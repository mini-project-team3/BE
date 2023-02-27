package com.sparta.be.entity;

import com.sparta.be.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String contents;

    // 댓글 N : 게시글 1, 주인 정하기
    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    // 댓글 N : 유저 1 , 주인 정하기
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(CommentRequestDto requestDto, User user) {
        this.contents = requestDto.getContents();
        this.user = user;
    }
    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}

