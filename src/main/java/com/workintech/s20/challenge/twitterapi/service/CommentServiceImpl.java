package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.CommentResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Comment;
import com.workintech.s20.challenge.twitterapi.exception.CommentNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.CommentMapper;
import com.workintech.s20.challenge.twitterapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public CommentResponseDto findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id + " id'li comment bulunamadı."));
    }

    @Override
    public CommentResponseDto save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public CommentResponseDto replaceOrCreate(Long id, Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            comment.setId(id);
            return commentRepository.save(comment);
        }
        return commentRepository.save(comment);
    }

    @Override
    public CommentResponseDto update(Long id, Comment comment) {
        Comment commentToUpdate = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id + " id'li comment bulunamadı."));

        if (comment.getContent() != null) {
            commentToUpdate.setContent(comment.getContent());
        }

        if (comment.getCommentedAt() != null) {
            commentToUpdate.setCommentedAt(comment.getCommentedAt());
        }

        if (comment.getTweet() != null) {
            commentToUpdate.setTweet(comment.getTweet());
        }

        if (comment.getUser() != null) {
            commentToUpdate.setUser(comment.getUser());
        }

        return commentRepository.save(commentToUpdate);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<CommentResponseDto> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public List<CommentResponseDto> findByTweetId(Long tweetId) {
        return commentRepository.findByTweetId(tweetId);
    }

}
