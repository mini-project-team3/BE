package com.sparta.be.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Builder
    private LikeComment(Comment comment, User user){
        this.comment = comment;
        this.user = user;
    }

    public static LikeComment of(Comment comment, User user){
        LikeComment likeComment = LikeComment.builder()
                .comment(comment)
                .user(user)
                .build();
        comment.getLikeCommentList().add(likeComment);
        return likeComment;
    }
}
