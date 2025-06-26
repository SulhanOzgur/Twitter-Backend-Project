package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.TweetResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto save(UserRequestDto userRequestDto);
    UserResponseDto replaceOrCreate(Long id, UserRequestDto userRequestDto);
    UserResponseDto update(Long id, UserRequestDto userRequestDto);
    void delete(Long id);

    List<TweetResponseDto> getTweetsByUserId(Long userId);
}
