package com.workintech.s20.challenge.twitterapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequestDto(
        @NotBlank
        @Size(max = 50)
        String code
) {
}
