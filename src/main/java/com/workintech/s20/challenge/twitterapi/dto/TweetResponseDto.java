package com.workintech.s20.challenge.twitterapi.dto;

import java.time.LocalDate;

public record TweetResponseDto(
        String content,
        LocalDate createdAt,
        Long userId,
        String userName
) {
}
