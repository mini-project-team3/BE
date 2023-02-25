package com.sparta.be.dto;

import com.sparta.be.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private int likeCount;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.nickname = review.getUser().getNickname();
        this.createdAt = review.getCreatedAt();
    }
}
