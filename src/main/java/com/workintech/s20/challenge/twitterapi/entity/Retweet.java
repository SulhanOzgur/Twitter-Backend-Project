package com.workintech.s20.challenge.twitterapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "retweet", schema = "s20challenge")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotEmpty
    @NotNull
    @NotBlank
    private Long id;

    @JoinColumn(name = "retweeted_at")
    @NotEmpty
    @NotNull
    @NotBlank
    private LocalDateTime retweetedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    @NotEmpty
    @NotNull
    @NotBlank
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotEmpty
    @NotNull
    @NotBlank
    private User user;


}
