package com.workintech.s20.challenge.twitterapi.exception;

import org.springframework.http.HttpStatus;

public class DislikeNotFoundException extends TwitterException {

    public DislikeNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
