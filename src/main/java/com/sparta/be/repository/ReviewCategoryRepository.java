package com.sparta.be.repository;

import com.sparta.be.entity.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory, Long> {
}
