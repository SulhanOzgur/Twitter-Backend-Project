package com.workintech.s20.challenge.twitterapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class TwitterErrorResponse {

    private String message;
    private int Status;
    private Long timestamp;
    private LocalDateTime localDateTime;

}
