package com.workintech.s20.challenge.twitterapi.mapper;

import com.workintech.s20.challenge.twitterapi.dto.CommentRequestDto;
import com.workintech.s20.challenge.twitterapi.dto.CommentResponseDto;
import com.workintech.s20.challenge.twitterapi.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        comment.setFirstName(commentRequestDto.firstName());
        comment.setLastName(commentRequestDto.lastName());
        comment.setUserName(commentRequestDto.userName());
        comment.setPassword(commentRequestDto.password());
        comment.setEmail(commentRequestDto.email());
        comment.setRole(commentRequestDto.role());

        return comment;
    }


    public CommentResponseDto toResponseDto(Comment comment) {
        return new CommentResponseDto(
                comment.getFirstName(),
                comment.getLastName(),
                comment.getUserName(),
                comment.getEmail(),
                comment.getRole()
        );
    }

}
