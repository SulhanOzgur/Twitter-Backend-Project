package com.workintech.s20.challenge.twitterapi.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends TwitterException {

    public RoleNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
