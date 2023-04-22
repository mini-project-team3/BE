package com.sparta.be.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewResponseDto {

    private Integer totalItems;
    private Integer totalPages;
    private Boolean isLastPage;
    private Boolean is2ndLastPage;
    private List<ReviewListResponseDto> reviewList;

    @Builder
    private ReviewResponseDto(Integer totalItems, Integer totalPages, Boolean isLastPage, Boolean is2ndLastPage, List<ReviewListResponseDto> reviewList) {
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.isLastPage = isLastPage;
        this.is2ndLastPage = is2ndLastPage;
        this.reviewList = reviewList;
    }

    public static ReviewResponseDto from(Integer totalItems, Integer totalPages, Boolean isLastPage, Boolean is2ndLastPage, List<ReviewListResponseDto> reviewList) {
        return ReviewResponseDto.builder()
                .totalItems(totalItems)
                .totalPages(totalPages)
                .isLastPage(isLastPage)
                .is2ndLastPage(is2ndLastPage)
                .reviewList(reviewList)
                .build();
    }

}
