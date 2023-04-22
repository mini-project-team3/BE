package com.sparta.be.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "CATEGORY")
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<ReviewCategory> reviewList = new ArrayList<>();

    @Builder
    private Category(CategoryType categoryType, List<ReviewCategory> reviewList) {
        this.categoryType = categoryType;
        this.reviewList = reviewList;
    }

    public static Category of(CategoryType categoryType) {
        return Category.builder()
                .categoryType(categoryType)
                .reviewList(new ArrayList<>())
                .build();
    }

    public void addCategory(ReviewCategory reviewCategory) {
        this.getReviewList().add(reviewCategory);
    }
}
