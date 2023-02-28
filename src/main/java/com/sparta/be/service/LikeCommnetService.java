package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorType;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.entity.Comment;
import com.sparta.be.entity.LikeComment;
import com.sparta.be.entity.User;
import com.sparta.be.repository.CommentRepository;
import com.sparta.be.repository.LikeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeCommnetService {

    private final LikeCommentRepository likeCommentRepository;

    private final CommentRepository commentRepsoitory;

    @Transactional
    public ApiResponseDto<Void> likeComment(Long id, User user){
        //댓글 확인
        Optional<Comment> comment = commentRepsoitory.findById(id);
        if (comment.isEmpty()){
            throw new IllegalArgumentException(ErrorType.NOT_FOUND_COMMENT_WRITING.getMessage());
        }


        Optional<LikeComment> found = likeCommentRepository.findByCommentAndUser(comment.get(),user);
        if (found.isEmpty()){
             comment.get().likeCommentUp();
            LikeComment likeComment = LikeComment.of(comment.get(), user);
            likeCommentRepository.save(likeComment);
        }else {
            throw new IllegalArgumentException(ErrorType.LIKE_ALREADY_DONE.getMessage());
        }

        return ResponseUtils.ok();
    }

    @Transactional
    public ApiResponseDto<Void> likeCancelComment(Long id, User user){
        //댓글 확인
        Optional<Comment> comment = commentRepsoitory.findById(id);
        if (comment.isEmpty()){
            throw new IllegalArgumentException(ErrorType.NOT_FOUND_COMMENT_WRITING.getMessage());
        }


        Optional<LikeComment> found = likeCommentRepository.findByCommentAndUser(comment.get(),user);
        if (found.isPresent()) {
            comment.get().likeCommentDown();
            likeCommentRepository.delete(found.get());
            likeCommentRepository.flush();
        }else {
            throw new IllegalArgumentException(ErrorType.LIKE_CANCLE_ALREADY_DONE.getMessage());
        }

        return ResponseUtils.ok();
    }


}
