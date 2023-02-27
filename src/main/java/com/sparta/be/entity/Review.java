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
@Table(name = "REVIEW")
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

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewCategory> categoryList = new ArrayList<>();

    private int likeCount;

    @Builder
    private Review(ReviewRequestDto requestDto, List<ReviewCategory> categoryList, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.categoryList = categoryList;
        this.user = user;
    }

    public static Review of(ReviewRequestDto request, User user) {
        return Review.builder()
                .requestDto(request)
                .user(user)
                .categoryList(new ArrayList<>())
                .build();
    }

    public void addCategory(ReviewCategory reviewCategory) {
        this.getCategoryList().add(reviewCategory);
    }

    public void update(ReviewRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
