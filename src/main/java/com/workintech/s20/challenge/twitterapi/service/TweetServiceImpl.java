package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.*;
import com.workintech.s20.challenge.twitterapi.entity.*;
import com.workintech.s20.challenge.twitterapi.exception.TweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.TweetMapper;
import com.workintech.s20.challenge.twitterapi.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, TweetMapper tweetMapper) {
        this.tweetRepository = tweetRepository;
        this.tweetMapper = tweetMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<TweetResponseDto> findAll() {
        return tweetRepository.findAll();
    }

    @Override
    public TweetResponseDto findById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id + " id'li tweet bulunamadı."));
    }

    @Override
    public TweetResponseDto save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public TweetResponseDto replaceOrCreate(Long id, Tweet tweet) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);

        if (optionalTweet.isPresent()) {
            tweet.setId(id);
            return tweetRepository.save(tweet);
        }
        return tweetRepository.save(tweet);
    }

    @Override
    public TweetResponseDto update(Long id, Tweet tweet) {
        Tweet tweetToUpdate = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id + " id'li tweet bulunamadı."));

        if (tweet.getContent() != null) {
            tweetToUpdate.setContent(tweet.getContent());
        }
        if (tweet.getCreatedAt() != null) {
            tweetToUpdate.setCreatedAt(tweet.getCreatedAt());
        }
        if (tweet.getUser() != null) {
            tweetToUpdate.setUser(tweet.getUser());
        }

        return tweetRepository.save(tweetToUpdate);
    }


    @Override
    public void delete(Long id) {
        tweetRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<TweetResponseDto> getTweetsByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public List<TweetResponseDto> searchTweetsByKeyword(String keyword) {
        return tweetRepository.searchByContent(keyword);
    }

    @Override
    public List<LikeResponseDto> getLikesByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getLikes();
    }

    @Override
    public List<DislikeResponseDto> getDislikesByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getDislikes();
    }

    @Override
    public List<RetweetResponseDto> getRetweetsByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getRetweets();
    }

    @Override
    public List<CommentResponseDto> getCommentsByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException(tweetId + " id'li tweet bulunamadı."));
        return tweet.getComments();
    }
}
