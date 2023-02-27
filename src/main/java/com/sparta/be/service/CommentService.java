package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.dto.CommentRequestDto;
import com.sparta.be.entity.Comment;
import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import com.sparta.be.repository.CommentRepository;
import com.sparta.be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    //댓글 생성
    @Transactional
    public ApiResponseDto<?> createComment(Long id, CommentRequestDto requestDto, User user) {
//        Review review = reviewRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당하는 리뷰가 없습니다."));
////
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty()) {
            throw new IllegalArgumentException("해당하는 리뷰가 없습니다.");
        }

//        Comment comment = new Comment(requestDto, user, review);
        Comment comment = new Comment(requestDto, user, review.get());
        commentRepository.saveAndFlush(comment);
        return ResponseUtils.ok();
    }

    //댓글 수정
    @Transactional
    public ApiResponseDto<?> updateComment(Long id, CommentRequestDto requestDto) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new IllegalArgumentException("해당하는 댓글이 없습니다.");
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
            throw new IllegalArgumentException("해당하는 댓글이 없습니다.");
        }
        commentRepository.deleteById(id);
        return ResponseUtils.ok();
    }
}