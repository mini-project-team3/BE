package com.sparta.be.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponseDto<T> {

    private boolean success;
    private T data;
    private ErrorResponse error;

    @Builder
    public ApiResponseDto(boolean success, T data, ErrorResponse error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

}
