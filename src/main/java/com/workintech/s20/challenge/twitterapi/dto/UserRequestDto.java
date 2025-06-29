package com.workintech.s20.challenge.twitterapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank
        @Size(max = 70)
        String firstName,

        @NotBlank
        @Size(max = 70)
        String lastName,

        @NotBlank
        @Size(max = 70)
        String userName,

        @NotBlank
        @Size(min = 8, max = 70)
        String password,

        @NotBlank
        @Email
        String email,

        @NotNull
        Long roleId
) {

}
