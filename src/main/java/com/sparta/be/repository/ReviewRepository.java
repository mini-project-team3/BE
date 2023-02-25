package com.sparta.be.repository;

import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByOrderByCreatedAtDesc();
    Optional<Review> findByIdAndUser(Long id, User user);
}
