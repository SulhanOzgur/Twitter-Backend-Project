package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.CommentRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.CommentResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        comment.setContent(commentRequestDto.content());

        return comment;
    }


    public CommentResponseDto toResponseDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getCommentedAt(),
                comment.getUser().getUserName(),
                comment.getTweet().getId()
        );
    }

}
