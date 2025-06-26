package com.workintech.s20.challenge.twitterapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class TwitterException extends RuntimeException {

    private String message;

    @Getter
    private HttpStatus httpStatus;


    public TwitterException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
