package com.workintech.s20.challenge.twitterapi.controller;

import com.workintech.s20.challenge.twitterapi.dto.TweetResponseDto;
import com.workintech.s20.challenge.twitterapi.dto.UserRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.UserResponseDto;
import com.workintech.s20.challenge.twitterapi.service.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* ******************************************************************************************* */

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@Positive @PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto save(@Validated @RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto replaceOrCreate(@Positive @PathVariable Long id, @Validated @RequestBody UserRequestDto userRequestDto) {
        return userService.replaceOrCreate(id, userRequestDto);
    }

    @PatchMapping("/{id}")
    public UserResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody UserRequestDto userRequestDto) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable Long id) {
        userService.delete(id);
    }

    /* ******************************************************************************************* */

    @GetMapping("/{id}/tweets")
    public List<TweetResponseDto> getTweetsByUser(@Positive @PathVariable Long id) {
        return userService.getTweetsByUserId(id);
    }

}

