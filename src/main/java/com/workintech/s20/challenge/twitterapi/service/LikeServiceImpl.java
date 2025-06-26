package com.workintech.s20.challenge.twitterapi.service;


import com.workintech.s20.challenge.twitterapi.dto.LikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Comment;
import com.workintech.s20.challenge.twitterapi.entity.Like;
import com.workintech.s20.challenge.twitterapi.exception.DislikeNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.LikeNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.LikeMapper;
import com.workintech.s20.challenge.twitterapi.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;
    private LikeMapper likeMapper;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    /* ******************************************************************************************* */


    @Override
    public List<LikeResponseDto> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public LikeResponseDto findById(Long id) {
        return likeRepository.findById(id).orElseThrow(() -> new LikeNotFoundException(id + " id'li like bulunamadı."));
    }

    @Override
    public LikeResponseDto save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public LikeResponseDto replaceOrCreate(Long id, Like like) {
        Optional<Like> optionalLike = likeRepository.findById(id);

        if (optionalLike.isPresent()) {
            like.setId(id);
            return likeRepository.save(like);
        }
        return likeRepository.save(like);
    }

    @Override
    public LikeResponseDto update(Long id, Like like) {
        Like likeToUpdate = likeRepository.findById(id)
                .orElseThrow(() -> new LikeNotFoundException(id + " id'li like bulunamadı."));

        if (like.getLikedAt() != null) {
            likeToUpdate.setLikedAt(like.getLikedAt());
        }
        if (like.getTweet() != null) {
            likeToUpdate.setTweet(like.getTweet());
        }
        if (like.getUser() != null) {
            likeToUpdate.setUser(like.getUser());
        }

        return likeRepository.save(likeToUpdate);
    }


    @Override
    public void delete(Long id) {
        likeRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<LikeResponseDto> findByUserId(Long userId) {
        return likeRepository.findByUserId(userId);
    }

    @Override
    public List<LikeResponseDto> findByTweetId(Long tweetId) {
        return likeRepository.findByTweetId(tweetId);
    }

}
