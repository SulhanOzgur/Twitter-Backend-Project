package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.RetweetRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RetweetResponseDto;

import java.util.List;

public interface RetweetService {

    List<RetweetResponseDto> findAll();
    RetweetResponseDto findById(Long id);
    RetweetResponseDto save(RetweetRequestDto retweetRequestDto);
    RetweetResponseDto replaceOrCreate(Long id, RetweetRequestDto retweetRequestDto);
    RetweetResponseDto update(Long id, RetweetRequestDto retweetRequestDto);
    void delete(Long id);

    List<RetweetResponseDto> findByUserId(Long userId);
    List<RetweetResponseDto> findByTweetId(Long tweetId);

}
