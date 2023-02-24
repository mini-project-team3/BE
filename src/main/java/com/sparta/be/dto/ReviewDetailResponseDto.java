package com.sparta.be.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReviewDetailResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likeCount;
    private boolean isWriter;
    private List<CommentResponseDto> commentList;

}
