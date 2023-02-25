package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.dto.ReviewDetailResponseDto;
import com.sparta.be.dto.ReviewRequestDto;
import com.sparta.be.dto.ReviewResponseDto;
import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import com.sparta.be.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private static final int PAGE_SIZE = 10;

    //게시글 작성
    @Transactional
    public ApiResponseDto<?> createReview(ReviewRequestDto requestDto, User user) {
        reviewRepository.saveAndFlush(new Review(requestDto, user));
        return ResponseUtils.ok();
    }

    //전체 게시글 조회
    @Transactional(readOnly = true)
    public ApiResponseDto<List<ReviewResponseDto>> getReviews(int pageNo, String criteria) {

        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));
        Page<ReviewResponseDto> page = reviewRepository.findAll(pageable).map(ReviewResponseDto::from);

        return ResponseUtils.ok(page.getContent());

    }

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

    //게시글 수정
    @Transactional
    public ApiResponseDto<?> update(Long id, ReviewRequestDto requestDto) {
        Review review = getReviewById(id);
        review.update(requestDto);
        reviewRepository.flush();
        return ResponseUtils.ok();
    }

    //게시글 삭제
    @Transactional
    public ApiResponseDto<?> deleteReview(Long id) {
        Review review = getReviewById(id);
        reviewRepository.deleteById(review.getId());
        return ResponseUtils.ok();
    }

    private Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
    }

}
