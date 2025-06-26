package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.DislikeResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Dislike;
import com.workintech.s20.challenge.twitterapi.exception.DislikeNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.DislikeMapper;
import com.workintech.s20.challenge.twitterapi.repository.DislikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DislikeServiceImpl implements DislikeService {

    private DislikeRepository dislikeRepository;
    private DislikeMapper dislikeMapper;

    @Autowired
    public DislikeServiceImpl(DislikeRepository dislikeRepository, DislikeMapper dislikeMapper) {
        this.dislikeRepository = dislikeRepository;
        this.dislikeMapper = dislikeMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<DislikeResponseDto> findAll() {
        return dislikeRepository.findAll();
    }

    @Override
    public DislikeResponseDto findById(Long id) {
        return dislikeRepository.findById(id).orElseThrow(() -> new DislikeNotFoundException(id + " id'li dislike bulunamadı."));
    }

    @Override
    public DislikeResponseDto save(Dislike dislike) {
        return dislikeRepository.save(dislike);
    }

    @Override
    public DislikeResponseDto replaceOrCreate(Long id, Dislike dislike) {
        Optional<Dislike> optionalDislike = dislikeRepository.findById(id);

        if (optionalDislike.isPresent()) {
            dislike.setId(id);
            return dislikeRepository.save(dislike);
        }
        return dislikeRepository.save(dislike);
    }

    @Override
    public DislikeResponseDto update(Long id, Dislike dislike) {
        Dislike dislikeToUpdate = dislikeRepository.findById(id)
                .orElseThrow(() -> new DislikeNotFoundException(id + " id'li dislike bulunamadı."));

        if (dislike.getDislikedAt() != null) {
            dislikeToUpdate.setDislikedAt(dislike.getDislikedAt());
        }

        if (dislike.getTweet() != null) {
            dislikeToUpdate.setTweet(dislike.getTweet());
        }

        if (dislike.getUser() != null) {
            dislikeToUpdate.setUser(dislike.getUser());
        }

        return dislikeRepository.save(dislikeToUpdate);
    }

    @Override
    public void delete(Long id) {
        dislikeRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<DislikeResponseDto> findByUserId(Long userId) {
        return dislikeRepository.findByUserId(userId);
    }

    @Override
    public List<DislikeResponseDto> findByTweetId(Long tweetId) {
        return dislikeRepository.findByTweetId(tweetId);
    }
}
