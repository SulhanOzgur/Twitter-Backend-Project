package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.RetweetRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RetweetResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Retweet;
import org.springframework.stereotype.Component;

@Component
public class RetweetMapper {

    public Retweet toEntity(RetweetRequestDto retweetRequestDto) {
        Retweet retweet = new Retweet();
        return retweet;
    }


    public RetweetResponseDto toResponseDto(Retweet retweet) {
        return new RetweetResponseDto(
                retweet.getId(),
                retweet.getRetweetedAt(),
                retweet.getTweet().getContent(),
                retweet.getUser().getUserName()
        );
    }

}
