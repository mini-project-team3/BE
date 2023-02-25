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
        this.likeCount = review.getLikeReview() != null ? review.getLikeReview().size() : 0; // 리스트에 저장된 목록이 null이 아닐때 리스트에 저장된 좋아요 갯수를 반환 하고 맞으면 0으로 반환
    }
}
