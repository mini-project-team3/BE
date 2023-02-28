package com.sparta.be.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FailureResponseDto<T> {
    private boolean success;
    private ErrorResponse error;

    @Builder
    public FailureResponseDto(boolean success, ErrorResponse error) {
        this.success = success;
        this.error = error;
    }
}
