package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.TweetRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.TweetResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TweetMapper {

    public Tweet toEntity(TweetRequestDto tweetRequestDto) {
       Tweet tweet = new Tweet();
        tweet.setContent(tweetRequestDto.content());
        tweet.setCreatedAt(LocalDate.now());

        return tweet;
    }


    public TweetResponseDto toResponseDto(Tweet tweet) {
        return new TweetResponseDto(
                tweet.getContent(),
                tweet.getCreatedAt(),
                tweet.getUser().getId(),
                tweet.getUser().getUserName()
        );
    }

}
