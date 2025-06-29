package com.workintech.s20.challenge.twitterapi.dto;

public record UserResponseDto(
        String firstName,
        String lastName,
        String userName,
        String email,
        String roleCode
) {
}
