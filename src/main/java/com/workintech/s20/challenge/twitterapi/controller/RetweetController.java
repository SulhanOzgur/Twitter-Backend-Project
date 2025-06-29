package com.workintech.s20.challenge.twitterapi.controller;

import com.workintech.s20.challenge.twitterapi.dto.RetweetRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.RetweetResponseDto;
import com.workintech.s20.challenge.twitterapi.service.RetweetService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retweets")
public class RetweetController {

    private RetweetService retweetService;

    @Autowired
    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    /* ******************************************************************************************* */

    @GetMapping
    public List<RetweetResponseDto> findAll() {
        return retweetService.findAll();
    }

    @GetMapping("/{id}")
    public RetweetResponseDto findById(@Positive @PathVariable Long id) {
        return retweetService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RetweetResponseDto save(@Validated @RequestBody RetweetRequestDto retweetRequestDto) {
        return retweetService.save(retweetRequestDto);
    }

    @PutMapping("/{id}")
    public RetweetResponseDto replaceOrCreate(@Positive @PathVariable Long id, @Validated @RequestBody RetweetRequestDto retweetRequestDto) {
        return retweetService.replaceOrCreate(id, retweetRequestDto);
    }

    @PatchMapping("/{id}")
    public RetweetResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody RetweetRequestDto retweetRequestDto) {
        return retweetService.update(id, retweetRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable Long id) {
        retweetService.delete(id);
    }

    /* ******************************************************************************************* */

    @GetMapping("/byUser/{userId}")
    public List<RetweetResponseDto> findByUserId(@Positive @PathVariable Long userId) {
        return retweetService.findByUserId(userId);
    }

    @GetMapping("/byTweet/{tweetId}")
    public List<RetweetResponseDto> findByTweetId(@Positive @PathVariable Long tweetId) {
        return retweetService.findByTweetId(tweetId);
    }

}
