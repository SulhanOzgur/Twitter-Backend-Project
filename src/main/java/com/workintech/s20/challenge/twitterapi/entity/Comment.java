package com.workintech.s20.challenge.twitterapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment", schema = "s20challenge")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotEmpty
    @NotNull
    @NotBlank
    private Long id;

    @Column(name = "content")
    @Size(max = 600)
    @NotEmpty
    @NotNull
    @NotBlank
    private String content;

    @JoinColumn(name = "commented_at")
    @NotEmpty
    @NotNull
    @NotBlank
    private LocalDateTime commentedAt = LocalDateTime.now();

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
