package com.sparta.be.entity;

import com.sparta.be.dto.ReviewRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 65535)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int likeCount;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    private List<CategoryType> categoryList = new ArrayList<>();

    @Builder
    private Review(ReviewRequestDto requestDto, List<CategoryType> category, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.categoryList = category;
        this.user = user;
    }

    public static Review of(ReviewRequestDto request, List<CategoryType> category, User user) {
        return Review.builder()
                .requestDto(request)
                .user(user)
                .category(category)
                .build();
    }

    public void update(ReviewRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
