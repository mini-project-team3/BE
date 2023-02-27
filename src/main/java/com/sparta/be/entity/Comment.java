package com.sparta.be.entity;

import com.sparta.be.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "comment")
    private List<LikeComment> likeCommentList = new ArrayList<>();

    private int likeCount;
    public Comment(CommentRequestDto requestDto, User user, Review review) {
        this.contents = requestDto.getContents();
        this.user = user;
        this.review = review;
        this.likeCount = 0;
    }
    
    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }


    public void likeCommentUp(){
        this.likeCount += 1;
    }

    public void likeCommentDown(){
        this.likeCount -= 1;
    }

}
