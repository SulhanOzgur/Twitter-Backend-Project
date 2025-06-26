package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.DislikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.DislikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Comment;
import com.workintech.s20.challenge.twitterapi.entity.Dislike;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;

import java.util.List;

public interface DislikeService {

    List<DislikeResponseDto> findAll();
    DislikeResponseDto  findById(Long id);
    DislikeResponseDto save(DislikeRequestDto dislikeRequestDto);
    DislikeResponseDto replaceOrCreate(Long id, DislikeRequestDto dislikeRequestDto);
    DislikeResponseDto update(Long id, DislikeRequestDto dislikeRequestDto);
    void delete(Long id);

    List<DislikeResponseDto> findByUserId(Long userId);
    List<DislikeResponseDto> findByTweetId(Long tweetId);

}
