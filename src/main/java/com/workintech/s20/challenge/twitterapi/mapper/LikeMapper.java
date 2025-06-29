package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.LikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.LikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public Like toEntity(LikeRequestDto likeRequestDto) {
        Like like = new Like();

        return like;
    }


    public LikeResponseDto toResponseDto(Like like) {
        return new LikeResponseDto(
                like.getId(),
                like.getUser().getUserName(),
                like.getTweet().getContent()
        );
    }

}
