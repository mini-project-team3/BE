package com.sparta.be.repository;

import com.sparta.be.entity.Review;
import com.sparta.be.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAll(Pageable pageable);
    Optional<Review> findByIdAndUser(Long id, User user);
}
