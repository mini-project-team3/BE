package com.sparta.be.common;

public class ResponseUtils {

    public static <T> ApiResponseDto<T> ok(T data) {
        return new ApiResponseDto<T>(true, data, null);
    }

    public static <T> ApiResponseDto<T> error(ErrorResponse error) {
        return new ApiResponseDto<T>(false, null, error);
    }

    public static <T> ApiResponseDto<T> ok() {
        return new ApiResponseDto<T>(true, null, null);
    }
}
