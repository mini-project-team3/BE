package com.sparta.be.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private int likeCount;

}
