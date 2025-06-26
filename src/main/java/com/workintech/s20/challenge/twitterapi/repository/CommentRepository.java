package com.workintech.s20.challenge.twitterapi.repository;

import com.workintech.s20.challenge.twitterapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(Long userId);
    List<Comment> findByTweetId(Long tweetId);

}
