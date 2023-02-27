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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeReviewService {

    private final LikeReviewRepository likeReviewRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ApiResponseDto<?> likeReview(Long id, User user){
        //리뷰 확인
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"리뷰가 존재하지 않습니다."));
        }


        Optional<LikeReview> found = likeReviewRepository.findByReviewAndUser(review.get(),user);
        if (found.isEmpty()){
            review.get().likeReviewUp();
            LikeReview likeReview = LikeReview.of(review.get(), user);
            likeReviewRepository.save(likeReview);
        }else {
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "좋아요가 이미 되어있습니다."));
        }

            return ResponseUtils.ok();
    }

    @Transactional
    public ApiResponseDto<?> likeCancelReview(Long id, User user){
        //리뷰 확인
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isEmpty()){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST,"리뷰가 존재하지 않습니다."));
        }


        Optional<LikeReview> found = likeReviewRepository.findByReviewAndUser(review.get(),user);
        if (found.isPresent()) {
            review.get().likeReviewDown();
            likeReviewRepository.delete(found.get());
            likeReviewRepository.flush();
        }else {
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "좋아요가 이미 취소 되어있습니다."));
        }

        return ResponseUtils.ok();
    }

}
