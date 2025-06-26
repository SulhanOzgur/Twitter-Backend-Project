package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.LikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.LikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Comment;
import com.workintech.s20.challenge.twitterapi.entity.Like;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;

import java.util.List;


public interface LikeService {

    List<LikeResponseDto> findAll();
    LikeResponseDto findById(Long id);
    LikeResponseDto save(LikeRequestDto likeRequestDto);
    LikeResponseDto replaceOrCreate(Long id, LikeRequestDto likeRequestDto);
    LikeResponseDto update(Long id, LikeRequestDto likeRequestDto);
    void delete(Long id);

    List<LikeResponseDto> findByUserId(Long userId);
    List<LikeResponseDto> findByTweetId(Long tweetId);

}
