package com.sparta.be.controller;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.LikeCommnetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class LikeCommentController { // 댓글 좋아요

    private final LikeCommnetService likeCommnetService;


    @PostMapping("/likes/{id}")
    public ApiResponseDto<?> likeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeCommnetService.likeComment(id, userDetails.getUser());
    }

    @DeleteMapping("/likes/{id}")
    public ApiResponseDto<?> likeCancelReview(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeCommnetService.likeCancelComment(id, userDetails.getUser());
    }

}
