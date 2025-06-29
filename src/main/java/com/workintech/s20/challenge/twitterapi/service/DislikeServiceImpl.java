package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.DislikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.DislikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Dislike;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;
import com.workintech.s20.challenge.twitterapi.entity.User;
import com.workintech.s20.challenge.twitterapi.exception.DislikeNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.TweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.UserNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.DislikeMapper;
import com.workintech.s20.challenge.twitterapi.repository.DislikeRepository;
import com.workintech.s20.challenge.twitterapi.repository.TweetRepository;
import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DislikeServiceImpl implements DislikeService {

    private final DislikeRepository dislikeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final DislikeMapper dislikeMapper;

    @Autowired
    public DislikeServiceImpl(DislikeRepository dislikeRepository,
                              UserRepository userRepository,
                              TweetRepository tweetRepository,
                              DislikeMapper dislikeMapper) {
        this.dislikeRepository = dislikeRepository;
        this.userRepository    = userRepository;
        this.tweetRepository   = tweetRepository;
        this.dislikeMapper     = dislikeMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<DislikeResponseDto> findAll() {
        return dislikeRepository.findAll()
                .stream()
                .map(dislikeMapper::toResponseDto)
                .toList();
    }

    @Override
    public DislikeResponseDto findById(Long id) {
        Dislike foundDislike = dislikeRepository.findById(id)
                .orElseThrow(() -> new DislikeNotFoundException(id + " id'li dislike bulunamadı."));
        return dislikeMapper.toResponseDto(foundDislike);
    }

    @Override
    public DislikeResponseDto save(DislikeRequestDto dislikeRequestDto) {

        Dislike dislike = dislikeMapper.toEntity(dislikeRequestDto);

        User user = userRepository.findById(dislikeRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + dislikeRequestDto.userId()));
        Tweet tweet = tweetRepository.findById(dislikeRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + dislikeRequestDto.tweetId()));

        dislike.setUser(user);
        dislike.setTweet(tweet);

        Dislike saved = dislikeRepository.save(dislike);
        return dislikeMapper.toResponseDto(saved);
    }

    @Override
    public DislikeResponseDto replaceOrCreate(Long id, DislikeRequestDto dislikeRequestDto) {
        Dislike dislike = dislikeMapper.toEntity(dislikeRequestDto);
        dislike.setId(id);

        User user = userRepository.findById(dislikeRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + dislikeRequestDto.userId()));
        Tweet tweet = tweetRepository.findById(dislikeRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + dislikeRequestDto.tweetId()));

        dislike.setUser(user);
        dislike.setTweet(tweet);

        Dislike saved = dislikeRepository.save(dislike);
        return dislikeMapper.toResponseDto(saved);
    }

    @Override
    public DislikeResponseDto update(Long id, DislikeRequestDto dislikeRequestDto) {
        Dislike existing = dislikeRepository.findById(id)
                .orElseThrow(() -> new DislikeNotFoundException(id + " id'li dislike bulunamadı."));

        if (dislikeRequestDto.tweetId() != null) {
            Tweet tweet = tweetRepository.findById(dislikeRequestDto.tweetId())
                    .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + dislikeRequestDto.tweetId()));
            existing.setTweet(tweet);
        }
        if (dislikeRequestDto.userId() != null) {
            User user = userRepository.findById(dislikeRequestDto.userId())
                    .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + dislikeRequestDto.userId()));
            existing.setUser(user);
        }

        Dislike saved = dislikeRepository.save(existing);
        return dislikeMapper.toResponseDto(saved);
    }

    @Override
    public void delete(Long id) {
        dislikeRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<DislikeResponseDto> findByUserId(Long userId) {
        return dislikeRepository.findByUserId(userId)
                .stream()
                .map(dislikeMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<DislikeResponseDto> findByTweetId(Long tweetId) {
        return dislikeRepository.findByTweetId(tweetId)
                .stream()
                .map(dislikeMapper::toResponseDto)
                .toList();
    }
}
