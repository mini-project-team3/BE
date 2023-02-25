package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.entity.LikeComment;
import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import com.sparta.be.repository.LikeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeCommnetService {

    private final LikeCommentRepository likecommentRepository;

    private final CommentRepsoitory commentRepsoitory;

    @Transactional
    public ApiResponseDto<?> likeComment(Long id, User user){
        //댓글 확인
        if (commentRepsoitory.findById(id).isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"리뷰가 존재하지 않습니다."));
        }
        Review review = commentRepsoitory.findById(id).get();

        LikeComment likeComment = likecommentRepository.findByCommentAndUser(comment,user).get();
        if (likecommentRepository.findByCommentAndUser(comment, user).isEmpty()){
            likecommentRepository.save(likeComment);
        }
        return ResponseUtils.ok();
    }
}
