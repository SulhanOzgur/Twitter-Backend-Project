package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.*;

import java.util.List;

public interface TweetService {

    List<TweetResponseDto> findAll();
    TweetResponseDto findById(Long id);
    TweetResponseDto save(TweetRequestDto tweetRequestDto);
    TweetResponseDto replaceOrCreate(Long id, TweetRequestDto tweetRequestDto);
    TweetResponseDto update(Long id, TweetRequestDto tweetRequestDto);
    void delete(Long id);

    /* ******************************************************************************************* */

    List<TweetResponseDto> getTweetsByUserId(Long userId);
    List<TweetResponseDto> searchTweetsByKeyword(String keyword);

    List<LikeResponseDto> getLikesByTweetId(Long tweetId);
    List<DislikeResponseDto> getDislikesByTweetId(Long tweetId);
    List<RetweetResponseDto> getRetweetsByTweetId(Long tweetId);
    List<CommentResponseDto> getCommentsByTweetId(Long tweetId);

}
