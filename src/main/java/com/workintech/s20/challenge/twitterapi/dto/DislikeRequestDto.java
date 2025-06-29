package com.workintech.s20.challenge.twitterapi.dto;

public record DislikeRequestDto(
        Long tweetId,
        Long userId
) {
}
