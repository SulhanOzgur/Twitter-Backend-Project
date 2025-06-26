package com.workintech.s20.challenge.twitterapi;

import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TwitterapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterapiApplication.class, args);
	}

}
