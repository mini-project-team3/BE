package com.sparta.be.service;

import com.sparta.be.common.SuccessResponseDto;
import com.sparta.be.common.ErrorType;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.entity.LikeReview;
import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import com.sparta.be.repository.LikeReviewRepository;
import com.sparta.be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeReviewService {

    private final LikeReviewRepository likeReviewRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public SuccessResponseDto<Void> likeReview(Long id, User user){
        //리뷰 확인
        Review review = reviewRepository.findById(id).orElseThrow(
                () ->   new IllegalArgumentException(ErrorType.NOT_FOUND_REVIEW_WRITING.getMessage())
        );

        Optional<LikeReview> found = likeReviewRepository.findByReviewAndUser(review,user);
        if (found.isEmpty()){
            review.likeReviewUp();
            LikeReview likeReview = LikeReview.of(review, user);
            likeReviewRepository.save(likeReview);
        }else {
           throw new IllegalArgumentException(ErrorType.LIKE_ALREADY_DONE.getMessage());
        }

            return ResponseUtils.ok();
    }

    @Transactional
    public SuccessResponseDto<Void> likeCancelReview(Long id, User user){
        //리뷰 확인
        Review review = reviewRepository.findById(id).orElseThrow(
                () ->   new IllegalArgumentException(ErrorType.NOT_FOUND_REVIEW_WRITING.getMessage())
        );


        Optional<LikeReview> found = likeReviewRepository.findByReviewAndUser(review,user);
        if (found.isPresent()) {
            review.likeReviewDown();
            likeReviewRepository.delete(found.get());
            likeReviewRepository.flush();
        }else {
            throw new IllegalArgumentException(ErrorType.LIKE_CANCLE_ALREADY_DONE.getMessage());
        }

        return ResponseUtils.ok();
    }

}
