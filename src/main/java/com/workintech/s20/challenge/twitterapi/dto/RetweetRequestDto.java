package com.workintech.s20.challenge.twitterapi.dto;

public record RetweetRequestDto(
        Long tweetId,
        Long userId
) {
}
