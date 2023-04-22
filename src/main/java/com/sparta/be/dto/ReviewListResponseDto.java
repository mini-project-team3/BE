package com.sparta.be.dto;

import com.sparta.be.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewListResponseDto {

    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private int likeCount;

    @Builder
    private ReviewListResponseDto(Long id, String title, String nickname, LocalDateTime createdAt, int likeCount) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
    }

    public static ReviewListResponseDto from(Review entity) {
        return ReviewListResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .nickname(entity.getUser().getNickname())
                .createdAt(entity.getCreatedAt())
                .likeCount(entity.getLikeCount())
                .build();
    }
}
