package com.sparta.be.controller;

import com.sparta.be.common.SuccessResponseDto;
import com.sparta.be.dto.CommentRequestDto;
import com.sparta.be.security.UserDetailsImpl;
import com.sparta.be.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/api/reviews/{id}")
    public SuccessResponseDto<Void> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(id, requestDto, userDetails.getUser());
    }

    //댓글 수정
    @PutMapping("/api/comments/{id}")
    public SuccessResponseDto<Void> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public SuccessResponseDto<Void> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);

    }
}
