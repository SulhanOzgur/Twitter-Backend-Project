package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.UserRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.firstName());
        user.setLastName(userRequestDto.lastName());
        user.setUserName(userRequestDto.userName());
        user.setPassword(userRequestDto.password());
        user.setEmail(userRequestDto.email());

        return user;
    }


    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getRole().getCode()
        );
    }
}
