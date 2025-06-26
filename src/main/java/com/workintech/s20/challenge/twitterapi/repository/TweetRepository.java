package com.workintech.s20.challenge.twitterapi.repository;

import com.workintech.s20.challenge.twitterapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {


    List<Tweet> findByUserId(Long userId);

    @Query(value = "SELECT * FROM s20challenge.tweet WHERE content ILIKE %:keyword%", nativeQuery = true)
    List<Tweet> searchByContent(@Param("keyword") String keyword);


}
