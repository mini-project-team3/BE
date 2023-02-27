package com.sparta.be.repository;

import com.sparta.be.entity.Comment;
import com.sparta.be.entity.LikeComment;
import com.sparta.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findByCommentAndUser(Comment comment, User user);

}
