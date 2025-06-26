package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.RetweetRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RetweetResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Retweet;
import org.springframework.stereotype.Component;

@Component
public class RetweetMapper {

    public Retweet toEntity(RetweetRequestDto retweetRequestDto) {
        Retweet retweet = new Retweet();
        retweet.setFirstName(retweetRequestDto.firstName());
        retweet.setLastName(retweetRequestDto.lastName());
        retweet.setUserName(retweetRequestDto.userName());
        retweet.setPassword(retweetRequestDto.password());
        retweet.setEmail(retweetRequestDto.email());
        retweet.setRole(retweetRequestDto.role());

        return retweet;
    }


    public RetweetResponseDto toResponseDto(Retweet retweet) {
        return new RetweetResponseDto(
                retweet.getFirstName(),
                retweet.getLastName(),
                retweet.getUserName(),
                retweet.getEmail(),
                retweet.getRole()
        );
    }

}
