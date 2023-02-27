package com.sparta.be.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private CategoryType categoryType;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<ReviewCategory> categoryList = new ArrayList<>();

    @Builder
    private Category(CategoryType categoryType, List<ReviewCategory> categoryList) {
        this.categoryType = categoryType;
        this.categoryList = categoryList;
    }

    public static Category of(CategoryType categoryType) {
        return Category.builder()
                .categoryType(categoryType)
                .categoryList(new ArrayList<>())
                .build();
    }

    public void addCategory(ReviewCategory reviewCategory) {
        this.getCategoryList().add(reviewCategory);
    }
}
