package com.sparta.be.dto;

import com.sparta.be.entity.Review;
import lombok.Builder;
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
    private Boolean isWriter;
    private List<CommentResponseDto> commentList;

    @Builder
    private ReviewDetailResponseDto(Long id, String title, String contents, String nickname, LocalDateTime createdAt, LocalDateTime modifiedAt, int likeCount, boolean isWriter, List<CommentResponseDto> commentList) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.likeCount = likeCount;
        this.isWriter = isWriter;
        this.commentList = commentList;
    }

    public static ReviewDetailResponseDto from(Review entity, boolean isWriter) {
        return ReviewDetailResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .nickname(entity.getUser().getNickname())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .likeCount(entity.getLikeCount())
                .isWriter(isWriter)
                .commentList(entity.getCommentList().stream().map(CommentResponseDto::from).toList())
                .build();
    }
}
