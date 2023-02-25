package com.sparta.be.controller;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.dto.ReviewRequestDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //게시글 작성
    @PostMapping("/api/reviews")
    public ApiResponseDto<?> createReview(@RequestBody ReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reviewService.createReview(requestDto, userDetails.getUser());
    }

    //게시글 전체 조회
    @GetMapping("/api/reviews")
    public ApiResponseDto<?> getReviews(){
        return reviewService.getReviews();
    }

    //선택한 게시글 조회
    @GetMapping("/api/reviews/{id}")
    public ApiResponseDto<?> getReview(@PathVariable Long id){
        return reviewService.getReview(id);
    }

    //게시글 수정
    @PutMapping("/api/reviews/{id}")
    public ApiResponseDto<?> updateReview(@PathVariable Long id, @RequestBody ReviewRequestDto requestDto){
        return reviewService.update(id, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/api/reviews/{id}")
    public ApiResponseDto<?> deleteReview(@PathVariable Long id){
        return reviewService.deleteReview(id);
    }
}
