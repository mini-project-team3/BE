package com.sparta.be.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ReviewCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    private ReviewCategory(Review review, Category category) {
        this.review = review;
        this.category = category;
    }

    public static ReviewCategory of(Review review, Category category) {
        return ReviewCategory.builder()
                .review(review)
                .category(category)
                .build();
    }
}
