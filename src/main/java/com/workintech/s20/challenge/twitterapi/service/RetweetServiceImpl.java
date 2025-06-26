package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.RetweetResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Retweet;
import com.workintech.s20.challenge.twitterapi.exception.RetweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.RetweetMapper;
import com.workintech.s20.challenge.twitterapi.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetweetServiceImpl implements RetweetService {

    private RetweetRepository retweetRepository;
    private RetweetMapper retweetMapper;

    @Autowired
    public RetweetServiceImpl(RetweetRepository retweetRepository, RetweetMapper retweetMapper) {
        this.retweetRepository = retweetRepository;
        this.retweetMapper = retweetMapper;
    }

    /* ******************************************************************************************* */


    @Override
    public List<RetweetResponseDto> findAll() {
        return retweetRepository.findAll();
    }

    @Override
    public RetweetResponseDto findById(Long id) {
        return retweetRepository.findById(id).orElseThrow(() -> new RetweetNotFoundException(id + " id'li retweet bulunamadı."));
    }

    @Override
    public RetweetResponseDto save(Retweet retweet) {
        return retweetRepository.save(retweet);
    }

    @Override
    public RetweetResponseDto replaceOrCreate(Long id, Retweet retweet) {
        Optional<Retweet> optionalRetweet = retweetRepository.findById(id);

        if (optionalRetweet.isPresent()) {
            retweet.setId(id);
            return retweetRepository.save(retweet);
        }
        return retweetRepository.save(retweet);
    }

    @Override
    public RetweetResponseDto update(Long id, Retweet retweet) {
        Retweet retweetToUpdate = retweetRepository.findById(id)
                .orElseThrow(() -> new RetweetNotFoundException(id + " id'li retweet bulunamadı."));

        if (retweet.getRetweetedAt() != null) {
            retweetToUpdate.setRetweetedAt(retweet.getRetweetedAt());
        }
        if (retweet.getTweet() != null) {
            retweetToUpdate.setTweet(retweet.getTweet());
        }
        if (retweet.getUser() != null) {
            retweetToUpdate.setUser(retweet.getUser());
        }

        return retweetRepository.save(retweetToUpdate);
    }


    @Override
    public void delete(Long id) {
        retweetRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<RetweetResponseDto> findByUserId(Long userId) {
        return retweetRepository.findByUserId(userId);
    }

    @Override
    public List<RetweetResponseDto> findByTweetId(Long tweetId) {
        return retweetRepository.findByTweetId(tweetId);
    }

}
