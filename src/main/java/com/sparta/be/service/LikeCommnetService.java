package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.entity.*;
import com.sparta.be.repository.CommentRepository;
import com.sparta.be.repository.LikeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeCommnetService {

    private final LikeCommentRepository likeCommentRepository;

    private final CommentRepository commentRepsoitory;

    @Transactional
    public ApiResponseDto<?> likeComment(Long id, User user){
        //댓글 확인
        Optional<Comment> comment = commentRepsoitory.findById(id);
        if (comment.isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"댓글이 존재하지 않습니다."));
        }


        Optional<LikeComment> found = likeCommentRepository.findByCommentAndUser(comment.get(),user);
        if (found.isEmpty()){
             comment.get().likeCommentUp();
            LikeComment likeComment = LikeComment.of(comment.get(), user);
            likeCommentRepository.save(likeComment);
        }else {
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "좋아요가 이미 되어있습니다."));
        }

        return ResponseUtils.ok();
    }

    @Transactional
    public ApiResponseDto<?> likeCancelComment(Long id, User user){
        //댓글 확인
        Optional<Comment> comment = commentRepsoitory.findById(id);
        if (comment.isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"댓글이 존재하지 않습니다."));
        }


        Optional<LikeComment> found = likeCommentRepository.findByCommentAndUser(comment.get(),user);
        if (found.isPresent()) {
            comment.get().likeCommentDown();
            likeCommentRepository.delete(found.get());
            likeCommentRepository.flush();
        }else {
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "좋아요가 이미 취소 되어있습니다."));
        }

        return ResponseUtils.ok();
    }


}
