package com.sparta.be.common;

import lombok.Getter;

@Getter
public enum ErrorType {
    NOT_VALID_REQUEST(400, "유효하지 않은 요청입니다."),
    NOT_VALID_TOKEN(400, "토큰이 유효하지 않습니다."),
    NOT_TOKEN(400, "토큰이 없습니다."),
    NOT_WRITER(400, "작성자만 삭제/수정할 수 있습니다."),
    DUPLICATED_USERNAME(400, "중복된 아이디 입니다."),
    DUPLICATED_NICKNAME(400,"중복된 닉네임입니다."),
    NOT_MATCHING_INFO(400, "회원을 찾을 수 없습니다."),
    NOT_MATCHING_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_USER(400, "등록된 사용자가 없습니다."),
    NOT_FOUND_REVIEW_WRITING(400, "리뷰가 존재하지 않습니다."),
    NOT_FOUND_COMMENT_WRITING(400, "댓글이 존재하지 않습니다."),
    NOT_EXISTING_CATEGORY(400, "해당 카테고리가 존재하지 않습니다."),
    NOT_EXISTING_PAGE(400, "해당 페이지 번호에는 데이터가 존재하지 않습니다.");

    private int code;
    private String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
