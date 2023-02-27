package com.sparta.be.controller;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.dto.CommentRequestDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("reviews")
    public ApiResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto, userDetails.getUser());
    }

    //게시글 수정
    @PutMapping("/{id}")
    public ApiResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public ApiResponseDto<?> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);

    }
}

