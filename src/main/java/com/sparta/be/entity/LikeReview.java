package com.sparta.be.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Builder
    private LikeReview(Review review, User user){
        this.review = review;
        this.user = user;
    }

    public static LikeReview of(Review review, User user){
        LikeReview likeReview = LikeReview.builder()
                .review(review)
                .user(user)
                .build();
        review.getLikeReviewList().add(likeReview);
        return likeReview;
    }

}
