package com.workintech.s20.challenge.twitterapi.dto;

import java.time.LocalDateTime;

public record RetweetResponseDto(
        Long id,
        LocalDateTime retweetedAt,
        String tweetContent,
        String userName
) {
}
