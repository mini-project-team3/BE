package com.sparta.be.common;

public class ResponseUtils {

    public static <T> SuccessResponseDto<T> ok(T data) {
        return new SuccessResponseDto<T>(true, data);
    }

    public static FailureResponseDto error(ErrorResponse error) {
        return new FailureResponseDto(false, error);
    }

    public static <T> SuccessResponseDto<T> ok() {
        return new SuccessResponseDto<T>(true, null);
    }
}
