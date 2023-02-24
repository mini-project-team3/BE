package com.sparta.be.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private int status;
    private String message;

    @Builder
    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .status(status.value())
                .message(message)
                .build();
    }

    public static ErrorResponse of(int statusCode, String message) {
        return ErrorResponse.builder()
                .status(statusCode)
                .message(message)
                .build();
    }
}

