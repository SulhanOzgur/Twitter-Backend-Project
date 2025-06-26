package com.workintech.s20.challenge.twitterapi.controller;

import com.workintech.s20.challenge.twitterapi.dto.DislikeRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.DislikeResponseDto;
import com.workintech.s20.challenge.twitterapi.service.DislikeService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dislikes")
public class DislikeController {

    private DislikeService dislikeService;

    @Autowired
    public DislikeController(DislikeService dislikeService) {
        this.dislikeService = dislikeService;
    }

    /* ******************************************************************************************* */

    @GetMapping
    public List<DislikeResponseDto> findAll() {
        return dislikeService.findAll();
    }

    @GetMapping("/{id}")
    public DislikeResponseDto findById(@Positive @PathVariable Long id) {
        return dislikeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DislikeResponseDto save(@Validated @RequestBody DislikeRequestDto dislikeRequestDto) {
        return dislikeService.save(dislikeRequestDto);
    }

    @PutMapping("/{id}")
    public DislikeResponseDto replaceOrCreate(@Positive @PathVariable Long id, @Validated @RequestBody DislikeRequestDto dislikeRequestDto) {
        return dislikeService.replaceOrCreate(id, dislikeRequestDto);
    }

    @PatchMapping("/{id}")
    public DislikeResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody DislikeRequestDto dislikeRequestDto) {
        return dislikeService.update(id, dislikeRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable Long id) {
        dislikeService.delete(id);
    }

    /* ******************************************************************************************* */

    @GetMapping("/byUser/{userId}")
    public List<DislikeResponseDto> findByUserId(@Positive @PathVariable Long userId) {
        return dislikeService.findByUserId(userId);
    }

    @GetMapping("/byTweet/{tweetId}")
    public List<DislikeResponseDto> findByTweetId(@Positive @PathVariable Long tweetId) {
        return dislikeService.findByTweetId(tweetId);
    }
}
