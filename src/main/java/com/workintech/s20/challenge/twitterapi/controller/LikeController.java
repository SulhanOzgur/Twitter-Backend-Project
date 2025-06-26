package com.workintech.s20.challenge.twitterapi.controller;

import com.workintech.s20.challenge.twitterapi.dto.LikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.LikeResponseDto;
import com.workintech.s20.challenge.twitterapi.service.LikeService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    /* ******************************************************************************************* */

    @GetMapping
    public List<LikeResponseDto> findAll() {
        return likeService.findAll();
    }

    @GetMapping("/{id}")
    public LikeResponseDto findById(@Positive @PathVariable Long id) {
        return likeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto save(@Validated @RequestBody  LikeRequestDto likeRequestDto) {
        return likeService.save(likeRequestDto);
    }

    @PutMapping("/{id}")
    public LikeResponseDto replaceOrCreate(@Positive @PathVariable Long id, @Validated @RequestBody LikeRequestDto likeRequestDto) {
        return likeService.replaceOrCreate(id, likeRequestDto);
    }

    @PatchMapping("/{id}")
    public LikeResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody LikeRequestDto likeRequestDto) {
        return likeService.update(id, likeRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable Long id) {
        likeService.delete(id);
    }

    /* ******************************************************************************************* */

    @GetMapping("/byUser/{userId}")
    public List<LikeResponseDto> findByUserId(@Positive @PathVariable Long userId) {
        return likeService.findByUserId(userId);
    }

    @GetMapping("/byTweet/{tweetId}")
    public List<LikeResponseDto> findByTweetId(@Positive @PathVariable Long tweetId) {
        return likeService.findByTweetId(tweetId);
    }
}
