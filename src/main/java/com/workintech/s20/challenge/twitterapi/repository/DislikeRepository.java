package com.workintech.s20.challenge.twitterapi.repository;

import com.workintech.s20.challenge.twitterapi.entity.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DislikeRepository extends JpaRepository<Dislike, Long> {

    List<Dislike> findByUserId(Long userId);
    List<Dislike> findByTweetId(Long tweetId);

}
