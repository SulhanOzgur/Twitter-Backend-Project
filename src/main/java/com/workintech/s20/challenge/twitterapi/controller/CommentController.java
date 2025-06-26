package com.workintech.s20.challenge.twitterapi.controller;

import com.workintech.s20.challenge.twitterapi.dto.CommentRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.CommentResponseDto;
import com.workintech.s20.challenge.twitterapi.service.CommentService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /* ******************************************************************************************* */


    @GetMapping
    public List<CommentResponseDto> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public CommentResponseDto findById(@Positive @PathVariable Long id) {
        return commentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto save(@Validated @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.save(commentRequestDto);
    }

    @PutMapping("/{id}")
    public CommentResponseDto replaceOrCreate(@Positive @PathVariable Long id, @Validated @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.replaceOrCreate(id, commentRequestDto);
    }

    @PatchMapping("/{id}")
    public CommentResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.update(id, commentRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable Long id) {
        commentService.delete(id);
    }

    /* ******************************************************************************************* */

    @GetMapping("/byUser/{userId}")
    public List<CommentResponseDto> findByUserId(@Positive @PathVariable Long userId) {
        return commentService.findByUserId(userId);
    }

    @GetMapping("/byTweet/{tweetId}")
    public List<CommentResponseDto> findByTweetId(@Positive @PathVariable Long tweetId) {
        return commentService.findByTweetId(tweetId);
    }

}
