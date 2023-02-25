package com.sparta.be.dto;

import lombok.Getter;

import java.time.LocalDateTime;

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
//    private List<CommentResponseDto> commentList;
    //이건 연관관계 매핑 후에 다시 주석 풀고 쓰면 될 거 같습니다.

}
