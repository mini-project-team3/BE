package com.sparta.be.dto;


import com.sparta.be.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String nickname;
    private String contents;

    private int likeCount;

    @Builder
    private CommentResponseDto(Long id, String nickname, String contents) {
        this.id = id;
        this.nickname = nickname;
        this.contents = contents;
    }

    public static CommentResponseDto from(Comment entity) {
        return CommentResponseDto.builder()
                .id(entity.getId())
                .nickname(entity.getUser().getNickname())
                .contents(entity.getContents())
                .build();
    }
}
