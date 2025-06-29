package com.workintech.s20.challenge.twitterapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TweetRequestDto(
        @NotBlank
        @Size(max = 600)
        String content,

        @NotNull
        Long userId
) {
}
