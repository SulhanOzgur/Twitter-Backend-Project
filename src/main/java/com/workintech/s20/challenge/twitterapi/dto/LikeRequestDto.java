package com.workintech.s20.challenge.twitterapi.dto;

public record LikeRequestDto(
        Long tweetId,
        Long userId
) {
}
