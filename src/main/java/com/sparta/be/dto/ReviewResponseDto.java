package com.sparta.be.dto;

import com.sparta.be.entity.Review;
import lombok.Builder;
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
        this.likeCount = review.getLikeReviewList() != null ? review.getLikeReviewList().size() : 0; // 좋아요 갯수 반환
    }

    @Builder
    public ReviewResponseDto(Long id, String title, String nickname, LocalDateTime createdAt, int likeCount) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
    }

    public static ReviewResponseDto from(Review entity) {
        return ReviewResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .nickname(entity.getUser().getNickname())
                .createdAt(entity.getCreatedAt())
                .likeCount(entity.getLikeCount())
                .build();
    }

}
