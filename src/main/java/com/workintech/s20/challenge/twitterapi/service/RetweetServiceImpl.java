package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.RetweetRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RetweetResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Retweet;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;
import com.workintech.s20.challenge.twitterapi.entity.User;
import com.workintech.s20.challenge.twitterapi.exception.RetweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.TweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.UserNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.RetweetMapper;
import com.workintech.s20.challenge.twitterapi.repository.RetweetRepository;
import com.workintech.s20.challenge.twitterapi.repository.TweetRepository;
import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetweetServiceImpl implements RetweetService {

    private RetweetRepository retweetRepository;
    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private RetweetMapper retweetMapper;

    @Autowired
    public RetweetServiceImpl(RetweetRepository retweetRepository, TweetRepository tweetRepository,
                              UserRepository userRepository, RetweetMapper retweetMapper) {
        this.retweetRepository = retweetRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.retweetMapper = retweetMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<RetweetResponseDto> findAll() {
        return retweetRepository.findAll()
                .stream()
                .map(retweetMapper::toResponseDto)
                .toList();
    }

    @Override
    public RetweetResponseDto findById(Long id) {
        Retweet retweet = retweetRepository.findById(id)
                .orElseThrow(() -> new RetweetNotFoundException(id + " id'li retweet bulunamadı."));
        return retweetMapper.toResponseDto(retweet);
    }

    @Override
    public RetweetResponseDto save(RetweetRequestDto retweetRequestDto) {
        Retweet retweet = retweetMapper.toEntity(retweetRequestDto);

        Tweet tweet = tweetRepository.findById(retweetRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı"));
        User user = userRepository.findById(retweetRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));

        retweet.setTweet(tweet);
        retweet.setUser(user);

        return retweetMapper.toResponseDto(retweetRepository.save(retweet));
    }

    @Override
    public RetweetResponseDto replaceOrCreate(Long id, RetweetRequestDto retweetRequestDto) {
        Retweet retweet = retweetMapper.toEntity(retweetRequestDto);
        retweet.setId(id);

        Tweet tweet = tweetRepository.findById(retweetRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı"));
        User user = userRepository.findById(retweetRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));

        retweet.setTweet(tweet);
        retweet.setUser(user);

        return retweetMapper.toResponseDto(retweetRepository.save(retweet));
    }

    @Override
    public RetweetResponseDto update(Long id, RetweetRequestDto retweetRequestDto) {
        Retweet retweet = retweetRepository.findById(id)
                .orElseThrow(() -> new RetweetNotFoundException(id + " id'li retweet bulunamadı."));

        if (retweetRequestDto.tweetId() != null) {
            Tweet tweet = tweetRepository.findById(retweetRequestDto.tweetId())
                    .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı"));
            retweet.setTweet(tweet);
        }

        if (retweetRequestDto.userId() != null) {
            User user = userRepository.findById(retweetRequestDto.userId())
                    .orElseThrow(() -> new UserNotFoundException("User bulunamadı"));
            retweet.setUser(user);
        }

        return retweetMapper.toResponseDto(retweetRepository.save(retweet));
    }

    @Override
    public void delete(Long id) {
        retweetRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<RetweetResponseDto> findByUserId(Long userId) {
        return retweetRepository.findByUserId(userId)
                .stream()
                .map(retweetMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<RetweetResponseDto> findByTweetId(Long tweetId) {
        return retweetRepository.findByTweetId(tweetId)
                .stream()
                .map(retweetMapper::toResponseDto)
                .toList();
    }

}
