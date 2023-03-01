package com.sparta.be.controller;

import com.sparta.be.common.SuccessResponseDto;
import com.sparta.be.dto.ReviewDetailResponseDto;
import com.sparta.be.dto.ReviewRequestDto;
import com.sparta.be.dto.ReviewResponseDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //게시글 작성
    @PostMapping("/api/reviews")
    public SuccessResponseDto<Void> createReview(@RequestBody ReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reviewService.createReview(requestDto, userDetails.getUser());
    }

    //게시글 전체 조회
    @GetMapping("/api/reviews")
    public SuccessResponseDto<List<ReviewResponseDto>> getReviews(@RequestParam(required = false, defaultValue = "0", value = "category") int category,
                                                              @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                                              @RequestParam(required = false, defaultValue = "createdAt", value = "criteria") String criteria) {
        return reviewService.getReviews(category, pageNo, criteria);
    }

    // 게시글 상세 조회
    @GetMapping("/api/reviews/{id}")
    public SuccessResponseDto<ReviewDetailResponseDto> getReview(@PathVariable Long id){
        return reviewService.getReview(id);
    }

    // 내가 쓴 리뷰 조회
    @GetMapping("/api/myreviews")
    public SuccessResponseDto<List<ReviewResponseDto>> getMyReviews(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                    @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                                                    @RequestParam(required = false, defaultValue = "createdAt", value = "criteria") String criteria) {
        return reviewService.getMyReviews(pageNo, criteria, userDetails.getUser());
    }

    // 내가 좋아요한 리뷰 조회
    @GetMapping("/api/reviews/likes")
    public SuccessResponseDto<List<ReviewResponseDto>> getMyLikeReviews(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                        @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                                                        @RequestParam(required = false, defaultValue = "createdAt", value = "criteria") String criteria) {
        return reviewService.getMyLikeReviews(pageNo, criteria, userDetails.getUser());
    }

    //게시글 수정
    @PutMapping("/api/reviews/{id}")
    public SuccessResponseDto<Void> updateReview(@PathVariable Long id, @RequestBody ReviewRequestDto requestDto){
        return reviewService.update(id, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/api/reviews/{id}")
    public SuccessResponseDto<Void> deleteReview(@PathVariable Long id) {
        return reviewService.deleteReview(id);
    }

}
