package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.entity.LikeReview;
import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import com.sparta.be.repository.LikeReviewRepository;
import com.sparta.be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeReviewService {

    private final LikeReviewRepository likesRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ApiResponseDto<?> likeReview(Long id, User user){
        //리뷰 확인
        if (reviewRepository.findById(id).isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"리뷰가 존재하지 않습니다."));
        }
        Review review = reviewRepository.findById(id).get();

        LikeReview likes = likesRepository.findByReviewAndUser(review,user).get();
        if (likesRepository.findByReviewAndUser(review, user).isEmpty()){
            likesRepository.save(likes);
        }else{
            likesRepository.delete(likes);
            likesRepository.flush();
        }
            return ResponseUtils.ok();
    }

}
