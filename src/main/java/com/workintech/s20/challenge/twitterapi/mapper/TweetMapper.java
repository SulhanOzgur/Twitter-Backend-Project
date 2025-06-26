package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.TweetRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.TweetResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {

    public Tweet toEntity(TweetRequestDto tweetRequestDto) {
       Tweet tweet = new Tweet();
        tweet.setFirstName(tweetRequestDto.firstName());
        tweet.setLastName(tweetRequestDto.lastName());
        tweet.setUserName(tweetRequestDto.userName());
        tweet.setPassword(tweetRequestDto.password());
        tweet.setEmail(tweetRequestDto.email());
        tweet.setRole(tweetRequestDto.role());

        return tweet;
    }


    public TweetResponseDto toResponseDto(Tweet tweet) {
        return new TweetResponseDto(
                tweet.getFirstName(),
                tweet.getLastName(),
                tweet.getUserName(),
                tweet.getEmail(),
                tweet.getRole()
        );
    }

}
