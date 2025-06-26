package com.workintech.s20.challenge.twitterapi.service;
import com.workintech.s20.challenge.twitterapi.dto.CommentRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> findAll();
    CommentResponseDto findById(Long id);
    CommentResponseDto save(CommentRequestDto commentRequestDto);
    CommentResponseDto replaceOrCreate(Long id, CommentRequestDto commentRequestDto);
    CommentResponseDto update(Long id, CommentRequestDto commentRequestDto);
    void delete(Long id);

    List<CommentResponseDto> findByUserId(Long userId);
    List<CommentResponseDto> findByTweetId(Long tweetId);

}
