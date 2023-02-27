package com.sparta.be.repository;

import com.sparta.be.entity.LikeReview;
import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeReviewRepository extends JpaRepository<LikeReview, Long> {

    Optional<LikeReview> findByReviewAndUser(Review review, User uer);
}
