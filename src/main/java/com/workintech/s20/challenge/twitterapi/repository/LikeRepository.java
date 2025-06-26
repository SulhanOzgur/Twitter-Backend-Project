package com.workintech.s20.challenge.twitterapi.repository;

import com.workintech.s20.challenge.twitterapi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>  {

    List<Like> findByUserId(Long userId);
    List<Like> findByTweetId(Long tweetId);

}
