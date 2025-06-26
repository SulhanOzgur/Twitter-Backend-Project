package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.LikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.LikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public Like toEntity(LikeRequestDto likeRequestDto) {
        Like like = new Like();
        like.setFirstName(likeRequestDto.firstName());
        like.setLastName(likeRequestDto.lastName());
        like.setUserName(likeRequestDto.userName());
        like.setPassword(likeRequestDto.password());
        like.setEmail(likeRequestDto.email());
        like.setRole(likeRequestDto.role());

        return like;
    }


    public LikeResponseDto toResponseDto(Like like) {
        return new LikeResponseDto(
                like.getFirstName(),
                like.getLastName(),
                like.getUserName(),
                like.getEmail(),
                like.getRole()
        );
    }

}
