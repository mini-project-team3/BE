package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorType;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.dto.ReviewDetailResponseDto;
import com.sparta.be.dto.ReviewRequestDto;
import com.sparta.be.dto.ReviewResponseDto;
import com.sparta.be.entity.*;
import com.sparta.be.repository.CategoryRepository;
import com.sparta.be.repository.ReviewCategoryRepository;
import com.sparta.be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewCategoryRepository reviewCategoryRepository;
    private static final int PAGE_SIZE = 10;

    //게시글 작성
    @Transactional
    public ApiResponseDto<Void> createReview(ReviewRequestDto requestDto, User user) {

        Review review = Review.of(requestDto, user);
        List<ReviewCategory> categoryList = new ArrayList<>();

        for (String requestCategory : requestDto.getCategoryList()) {
            Category category = Category.of(CategoryType.valueOf(requestCategory));

            ReviewCategory reviewCategory = ReviewCategory.of(review, category);
            reviewCategoryRepository.save(reviewCategory);

            review.addCategory(reviewCategory);
            category.addCategory(reviewCategory);
            categoryRepository.save(category);

            categoryList.add(reviewCategory);
        }

        reviewRepository.save(review);

        return ResponseUtils.ok();
    }

    //전체 게시글 조회
    @Transactional(readOnly = true)
    public ApiResponseDto<List<ReviewResponseDto>> getReviews(int category, int pageNo, String criteria) {

        if (category > CategoryType.values().length) {
            throw new IllegalArgumentException(ErrorType.NOT_EXISTING_CATEGORY.getMessage());
        }

        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));
        Page<ReviewResponseDto> page;

        if (category == 0) {
            page = reviewRepository.findAll(pageable).map(ReviewResponseDto::from);
        } else {
            CategoryType categoryType = CategoryType.valueOf("C" + category);
            page = reviewRepository.findAllByCategoryListCategoryCategoryType(categoryType, pageable)
                    .map(ReviewResponseDto::from);
        }

        checkInvalidPage(pageNo, page.getTotalElements());

        return ResponseUtils.ok(page.getContent());
    }

    // 카테고리별 게시글 조회
//    @Transactional(readOnly = true)
//    public ApiResponseDto<List<ReviewResponseDto>> getReviewsByCategory(Long id, int pageNo, String criteria) {
//
//        if (id <= 0 || id > CategoryType.values().length) {
//            throw new IllegalArgumentException(ErrorType.NOT_EXISTING_CATEGORY.getMessage());
//        }
//
//        CategoryType category = CategoryType.valueOf("C" + id);
//        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));
//
//        Page<ReviewResponseDto> reviewPage = reviewRepository.findAllByCategoryListCategoryCategoryType(category, pageable).map(ReviewResponseDto::from);
//
//        checkInvalidPage(pageNo, reviewPage.getTotalElements());
//
//        return ResponseUtils.ok(reviewPage.getContent());
//    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public ApiResponseDto<ReviewDetailResponseDto> getReview(Long id, User user) {

        Review review = getReviewById(id);

        boolean isWriter = false;
        Optional<Review> found = reviewRepository.findByIdAndUser(id, user);
        if (found.isPresent()) {
            isWriter = true;
        }

        ReviewDetailResponseDto responseDto = ReviewDetailResponseDto.from(review, isWriter);

        return ResponseUtils.ok(responseDto);
    }

    // 내가 쓴 리뷰 조회
    @Transactional(readOnly = true)
    public ApiResponseDto<List<ReviewResponseDto>> getMyReviews(int pageNo, String criteria, User user) {

        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));
        Page<ReviewResponseDto> page = reviewRepository.findAllByUser(user, pageable).map(ReviewResponseDto::from);

        checkInvalidPage(pageNo, page.getTotalElements());

        return ResponseUtils.ok(page.getContent());
    }

    // 내가 좋아요한 리뷰 조회
    @Transactional(readOnly = true)
    public ApiResponseDto<List<ReviewResponseDto>> getMyLikeReviews(int pageNo, String criteria, User user) {

        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));
        Page<ReviewResponseDto> page = reviewRepository.findAllByLikeReviewListUser(user, pageable).map(ReviewResponseDto::from);

        return ResponseUtils.ok(page.getContent());
    }

    //게시글 수정
    @Transactional
    public ApiResponseDto<Void> update(Long id, ReviewRequestDto requestDto) {

        Review review = getReviewById(id);

        review.update(requestDto);
        reviewRepository.flush();

        return ResponseUtils.ok();
    }

    //게시글 삭제
    @Transactional
    public ApiResponseDto<Void> deleteReview(Long id) {

        Review review = getReviewById(id);

        reviewRepository.deleteById(review.getId());

        return ResponseUtils.ok();
    }

    private Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(ErrorType.NOT_FOUND_REVIEW_WRITING.getMessage())
        );
    }

    private void checkInvalidPage(int pageNo, Long totalElements) {
        if (pageNo > totalElements / PAGE_SIZE) {
            throw new IllegalArgumentException(ErrorType.NOT_EXISTING_PAGE.getMessage());
        }
    }
}
