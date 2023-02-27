package com.sparta.be.repository;

import com.sparta.be.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAll(Pageable pageable);
    List<Review> findAll();
    Optional<Review> findByIdAndUser(Long id, User user);
    Page<Review> findAllByCategoryListCategoryCategoryType(CategoryType categoryType, Pageable pageable);
    Page<Review> findAllByUser(User user, Pageable pageable);
}
