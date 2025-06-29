package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.DislikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.DislikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Dislike;
import org.springframework.stereotype.Component;

@Component
public class DislikeMapper {

    public Dislike toEntity(DislikeRequestDto dislikeRequestDto) {
       Dislike dislike = new Dislike();

        return dislike;
    }


    public DislikeResponseDto toResponseDto(Dislike dislike) {
        return new DislikeResponseDto(
                dislike.getId(),
                dislike.getUser().getUserName(),
                dislike.getTweet().getContent()
        );
    }

}
