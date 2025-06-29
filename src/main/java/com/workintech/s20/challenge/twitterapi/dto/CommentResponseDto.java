package com.workintech.s20.challenge.twitterapi.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        String content,
        LocalDateTime commentedAt,
        String userName,
        Long tweetId
) {
}
