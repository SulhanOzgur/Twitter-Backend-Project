package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.DislikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.DislikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Dislike;
import org.springframework.stereotype.Component;

@Component
public class DislikeMapper {

    public Dislike toEntity(DislikeRequestDto dislikeRequestDto) {
       Dislike dislike = new Dislike();
        dislike.setFirstName(dislikeRequestDto.firstName());
        dislike.setLastName(dislikeRequestDto.lastName());
        dislike.setUserName(dislikeRequestDto.userName());
        dislike.setPassword(dislikeRequestDto.password());
        dislike.setEmail(dislikeRequestDto.email());
        dislike.setRole(dislikeRequestDto.role());

        return dislike;
    }


    public DislikeResponseDto toResponseDto(Dislike dislike) {
        return new DislikeResponseDto(
                dislike.getFirstName(),
                dislike.getLastName(),
                dislike.getUserName(),
                dislike.getEmail(),
                dislike.getRole()
        );
    }

}
