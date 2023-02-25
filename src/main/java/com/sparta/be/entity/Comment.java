package com.sparta.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
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
    @Column(nullable = false)
    private Review review;

    // 댓글 N : 유저 1 , 주인 정하기
    @ManyToOne
    @Column(nullable = false)
    private User user;


    public Comment(String contents, Review review, User user) {
        this.contents = contents;
        this.review = review;
        this.user = user;
    }
}
