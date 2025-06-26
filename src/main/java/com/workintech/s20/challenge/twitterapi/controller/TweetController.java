package com.workintech.s20.challenge.twitterapi.controller;

import com.workintech.s20.challenge.twitterapi.dto.*;
import com.workintech.s20.challenge.twitterapi.service.TweetService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    /* ******************************************************************************************* */

    @GetMapping
    public List<TweetResponseDto> getAll() {
        return tweetService.findAll();
    }

    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@Positive @PathVariable Long id) {
        return tweetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto createTweet(@Validated @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.save(tweetRequestDto);
    }

    @PutMapping("/{id}")
    public TweetResponseDto replaceOrCreate(@Positive @PathVariable Long id, @Validated @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.replaceOrCreate(id, tweetRequestDto);
    }

    @PatchMapping("/{id}")
    public TweetResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.update(id, tweetRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweet(@Positive @PathVariable Long id) {
        tweetService.delete(id);
    }


    /* ******************************************************************************************* */


    @GetMapping("/user")
    public List<TweetResponseDto> getTweetsByUserId(@Positive @RequestParam Long userId) {
        return tweetService.getTweetsByUserId(userId);
    }

    @GetMapping("/search")
    public List<TweetResponseDto> searchByContent(@RequestParam String keyword) {
        return tweetService.searchTweetsByKeyword(keyword);
    }

    @GetMapping("/{id}/likes")
    public List<LikeResponseDto> getLikesByTweet(@Positive @PathVariable Long id) {
        return tweetService.getLikesByTweetId(id);
    }

    @GetMapping("/{id}/dislikes")
    public List<DislikeResponseDto> getDislikesByTweet(@Positive @PathVariable Long id) {
        return tweetService.getDislikesByTweetId(id);
    }

    @GetMapping("/{id}/retweets")
    public List<RetweetResponseDto> getRetweetsByTweet(@Positive @PathVariable Long id) {
        return tweetService.getRetweetsByTweetId(id);
    }

    @GetMapping("/{id}/comments")
    public List<CommentResponseDto> getCommentsByTweet(@Positive @PathVariable Long id) {
        return tweetService.getCommentsByTweetId(id);
    }

}
