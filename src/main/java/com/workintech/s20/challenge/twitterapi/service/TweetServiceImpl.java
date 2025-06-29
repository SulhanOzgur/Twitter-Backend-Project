package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.*;
import com.workintech.s20.challenge.twitterapi.entity.*;
import com.workintech.s20.challenge.twitterapi.exception.TweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.UserNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.*;
import com.workintech.s20.challenge.twitterapi.repository.TweetRepository;
import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {


    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private TweetMapper tweetMapper;
    private LikeMapper likeMapper;
    private DislikeMapper dislikeMapper;
    private RetweetMapper retweetMapper;
    private CommentMapper commentMapper;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository,
                            UserRepository userRepository,
                            TweetMapper tweetMapper,
                            LikeMapper likeMapper,
                            DislikeMapper dislikeMapper,
                            RetweetMapper retweetMapper,
                            CommentMapper commentMapper) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.tweetMapper = tweetMapper;
        this.likeMapper = likeMapper;
        this.dislikeMapper = dislikeMapper;
        this.retweetMapper = retweetMapper;
        this.commentMapper = commentMapper;
    }


    /* ******************************************************************************************* */

    @Override
    public List<TweetResponseDto> findAll() {
        return tweetRepository.findAll()
                .stream()
                .map(tweetMapper::toResponseDto)
                .toList();
    }

    @Override
    public TweetResponseDto findById(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id + " id'li tweet bulunamadı."));
        return tweetMapper.toResponseDto(tweet);
    }

    @Override
    public TweetResponseDto save(TweetRequestDto tweetRequestDto) {
        User user = userRepository.findById(tweetRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException(tweetRequestDto.userId() + " id'li user bulunamadı."));

        Tweet tweet = tweetMapper.toEntity(tweetRequestDto);
        tweet.setUser(user);

        Tweet saved = tweetRepository.save(tweet);
        return tweetMapper.toResponseDto(saved);
    }

    @Override
    public TweetResponseDto replaceOrCreate(Long id, TweetRequestDto tweetRequestDto) {
        Tweet tweet = tweetMapper.toEntity(tweetRequestDto);
        tweet.setId(id);

        User user = userRepository.findById(tweetRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException(tweetRequestDto.userId() + " id'li user bulunamadı."));

        tweet.setUser(user);
        tweet.setCreatedAt(LocalDate.now());

        return tweetMapper.toResponseDto(tweetRepository.save(tweet));
    }

    @Override
    public TweetResponseDto update(Long id, TweetRequestDto tweetRequestDto) {
        Tweet tweetToUpdate = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id + " id'li tweet bulunamadı."));

        if (tweetRequestDto.content() != null) {
            tweetToUpdate.setContent(tweetRequestDto.content());
        }

        if (tweetRequestDto.userId() != null) {
            User user = userRepository.findById(tweetRequestDto.userId())
                    .orElseThrow(() -> new UserNotFoundException(tweetRequestDto.userId() + " id'li user bulunamadı."));
            tweetToUpdate.setUser(user);
        }

        return tweetMapper.toResponseDto(tweetRepository.save(tweetToUpdate));
    }


    @Override
    public void delete(Long id) {
        tweetRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<TweetResponseDto> getTweetsByUserId(Long userId) {
        return tweetRepository.findByUserId(userId)
                .stream()
                .map(tweetMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<TweetResponseDto> searchTweetsByKeyword(String keyword) {
        return tweetRepository.searchByContent(keyword)
                .stream()
                .map(tweetMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<LikeResponseDto> getLikesByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getLikes()
                .stream()
                .map(likeMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<DislikeResponseDto> getDislikesByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getDislikes().stream()
                .map(dislikeMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<RetweetResponseDto> getRetweetsByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getRetweets()
                .stream()
                .map(retweetMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<CommentResponseDto> getCommentsByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getComments()
                .stream()
                .map(commentMapper::toResponseDto)
                .toList();
    }
}
