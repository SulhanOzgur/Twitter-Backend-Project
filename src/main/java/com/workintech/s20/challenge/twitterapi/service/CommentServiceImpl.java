package com.workintech.s20.challenge.twitterapi.service;

import com.workintech.s20.challenge.twitterapi.dto.CommentRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.CommentResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Comment;
import com.workintech.s20.challenge.twitterapi.entity.Tweet;
import com.workintech.s20.challenge.twitterapi.entity.User;
import com.workintech.s20.challenge.twitterapi.exception.CommentNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.TweetNotFoundException;
import com.workintech.s20.challenge.twitterapi.exception.UserNotFoundException;
import com.workintech.s20.challenge.twitterapi.mapper.CommentMapper;
import com.workintech.s20.challenge.twitterapi.repository.CommentRepository;
import com.workintech.s20.challenge.twitterapi.repository.TweetRepository;
import com.workintech.s20.challenge.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              TweetRepository tweetRepository,
                              UserRepository userRepository,
                              CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.tweetRepository   = tweetRepository;
        this.userRepository    = userRepository;
        this.commentMapper     = commentMapper;
    }

    /* ******************************************************************************************* */

    @Override
    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toResponseDto)
                .toList();
    }

    @Override
    public CommentResponseDto findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id + " id'li comment bulunamadı."));
        return commentMapper.toResponseDto(comment);
    }

    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {

        Comment comment = commentMapper.toEntity(commentRequestDto);

        Tweet tweet = tweetRepository.findById(commentRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + commentRequestDto.tweetId()));
        User user = userRepository.findById(commentRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + commentRequestDto.userId()));

        comment.setTweet(tweet);
        comment.setUser(user);

        Comment saved = commentRepository.save(comment);
        return commentMapper.toResponseDto(saved);
    }

    @Override
    public CommentResponseDto replaceOrCreate(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentMapper.toEntity(commentRequestDto);
        comment.setId(id);

        Tweet tweet = tweetRepository.findById(commentRequestDto.tweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + commentRequestDto.tweetId()));
        User user = userRepository.findById(commentRequestDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + commentRequestDto.userId()));

        comment.setTweet(tweet);
        comment.setUser(user);

        Comment saved = commentRepository.save(comment);
        return commentMapper.toResponseDto(saved);
    }

    @Override
    public CommentResponseDto update(Long id, CommentRequestDto commentRequestDto) {
        Comment existing = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id + " id'li comment bulunamadı."));

        if (commentRequestDto.content() != null) {
            existing.setContent(commentRequestDto.content());
        }
        if (commentRequestDto.tweetId() != null) {
            Tweet tweet = tweetRepository.findById(commentRequestDto.tweetId())
                    .orElseThrow(() -> new TweetNotFoundException("Tweet bulunamadı: " + commentRequestDto.tweetId()));
            existing.setTweet(tweet);
        }
        if (commentRequestDto.userId() != null) {
            User user = userRepository.findById(commentRequestDto.userId())
                    .orElseThrow(() -> new UserNotFoundException("User bulunamadı: " + commentRequestDto.userId()));
            existing.setUser(user);
        }

        Comment saved = commentRepository.save(existing);
        return commentMapper.toResponseDto(saved);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    /* ******************************************************************************************* */

    @Override
    public List<CommentResponseDto> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId)
                .stream()
                .map(commentMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<CommentResponseDto> findByTweetId(Long tweetId) {
        return commentRepository.findByTweetId(tweetId)
                .stream()
                .map(commentMapper::toResponseDto)
                .toList();
    }
}
