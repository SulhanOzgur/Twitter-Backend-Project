package com.workintech.s20.challenge.twitterapi.repository;

import com.workintech.s20.challenge.twitterapi.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {

    List<Retweet> findByUserId(Long userId);
    List<Retweet> findByTweetId(Long tweetId);

}
