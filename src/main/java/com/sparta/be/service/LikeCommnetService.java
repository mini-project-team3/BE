package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.entity.*;
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

    /*private final CommentRepsoitory commentRepsoitory;

    @Transactional
    public ApiResponseDto<?> likeComment(Long id, User user){
        //댓글 확인
        Optional<Comment> comment = commentRepsoitory.findById(id);
        if (comment.isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"리뷰가 존재하지 않습니다."));
        }


        Optional<LikeComment> likes = likeCommentRepository.findByCommentAndUser(comment.get(),user);
        if (likes.isEmpty()){
            likeCommentRepository.save(likes.get());
        }

        return ResponseUtils.ok();
    }

    @Transactional
    public ApiResponseDto<?> likeCancelComment(Long id, User user){
        //댓글 확인
        Optional<Comment> comment = commentRepsoitory.findById(id);
        if (comment.isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"리뷰가 존재하지 않습니다."));
        }


        Optional<LikeComment> likes = likeCommentRepository.findByCommentAndUser(comment.get(),user);
        if (!likes.isEmpty()) {
            likeCommentRepository.delete(likes.get());
            likeCommentRepository.flush();
        }

        return ResponseUtils.ok();
    }*/


}
