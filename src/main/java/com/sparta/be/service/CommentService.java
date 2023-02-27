package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.dto.CommentRequestDto;
import com.sparta.be.entity.Comment;
import com.sparta.be.entity.User;
import com.sparta.be.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 생성
    @Transactional
    public ApiResponseDto<?> createComment(CommentRequestDto requestDto, User user) {
        Comment comment = new Comment(requestDto, user);
        commentRepository.saveAndFlush(comment);
        return ResponseUtils.ok();
    }

    //댓글 수정
    @Transactional
    public ApiResponseDto<?> updateComment(Long id, CommentRequestDto requestDto) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new IllegalArgumentException("댓글이 존재하지 않습니다.");
        }
        comment.get().update(requestDto);
        commentRepository.flush();
        return ResponseUtils.ok();
    }

    //댓글 삭제
    @Transactional
    public ApiResponseDto<?> deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new IllegalArgumentException("댓글이 존재하지 않습니다.");
        }
        commentRepository.deleteById(id);
        return ResponseUtils.ok();
    }
}
