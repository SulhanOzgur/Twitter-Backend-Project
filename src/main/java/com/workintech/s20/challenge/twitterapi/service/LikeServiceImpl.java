package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.LikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.LikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Like;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;
import com.workintech.s20.challenge.twitterapi.entity.User;
import com.workintech.s20.challenge.twitterapi.exception.LikeNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.TweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.UserNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.LikeMapper;
import com.workintech.s20.challenge.twitterapi.repository.LikeRepository;
import com.workintech.s20.challenge.twitterapi.repository.TweetRepository;
import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final LikeMapper likeMapper;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository,
                           UserRepository userRepository,
                           TweetRepository tweetRepository,
                           LikeMapper likeMapper) {
        this.likeRepository  = likeRepository;
        this.userRepository  = userRepository;
        this.tweetRepository = tweetRepository;
        this.likeMapper      = likeMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<LikeResponseDto> findAll() {
        return likeRepository.findAll()
                .stream()
                .map(likeMapper::toResponseDto)
                .toList();
    }

    @Override
    public LikeResponseDto findById(Long id) {
        Like like = likeRepository.findById(id)
                .orElseThrow(() -> new LikeNotFoundException(id + " id'li like bulunamadı."));
        return likeMapper.toResponseDto(like);
    }

    @Override
    public LikeResponseDto save(LikeRequestDto likeRequestDto) {

        Like like = likeMapper.toEntity(likeRequestDto);

        User user = userRepository.findById(likeRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + likeRequestDto.userId()));
        Tweet tweet = tweetRepository.findById(likeRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + likeRequestDto.tweetId()));

        like.setUser(user);
        like.setTweet(tweet);

        Like saved = likeRepository.save(like);
        return likeMapper.toResponseDto(saved);
    }

    @Override
    public LikeResponseDto replaceOrCreate(Long id, LikeRequestDto likeRequestDto) {
        Like like = likeMapper.toEntity(likeRequestDto);
        like.setId(id);

        User user = userRepository.findById(likeRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + likeRequestDto.userId()));
        Tweet tweet = tweetRepository.findById(likeRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + likeRequestDto.tweetId()));

        like.setUser(user);
        like.setTweet(tweet);

        Like saved = likeRepository.save(like);
        return likeMapper.toResponseDto(saved);
    }

    @Override
    public LikeResponseDto update(Long id, LikeRequestDto likeRequestDto) {
        Like existing = likeRepository.findById(id)
                .orElseThrow(() -> new LikeNotFoundException(id + " id'li like bulunamadı."));

        if (likeRequestDto.tweetId() != null) {
            Tweet tweet = tweetRepository.findById(likeRequestDto.tweetId())
                    .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + likeRequestDto.tweetId()));
            existing.setTweet(tweet);
        }
        if (likeRequestDto.userId() != null) {
            User user = userRepository.findById(likeRequestDto.userId())
                    .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + likeRequestDto.userId()));
            existing.setUser(user);
        }

        Like saved = likeRepository.save(existing);
        return likeMapper.toResponseDto(saved);
    }

    @Override
    public void delete(Long id) {
        likeRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<LikeResponseDto> findByUserId(Long userId) {
        return likeRepository.findByUserId(userId)
                .stream()
                .map(likeMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<LikeResponseDto> findByTweetId(Long tweetId) {
        return likeRepository.findByTweetId(tweetId)
                .stream()
                .map(likeMapper::toResponseDto)
                .toList();
    }
}

