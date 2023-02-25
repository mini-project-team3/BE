package com.sparta.be.controller;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.LikeReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class LikeReviewController { // 게시글 좋아요

    private final LikeReviewService likeReviewService;

    @PostMapping("/likes/{id}")
    public ApiResponseDto<?> likeReview(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeReviewService.likeReview(id, userDetails.getUser());
    }

}
