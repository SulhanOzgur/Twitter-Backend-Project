package com.workintech.s20.challenge.twitterapi.repository;

import com.workintech.s20.challenge.twitterapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
