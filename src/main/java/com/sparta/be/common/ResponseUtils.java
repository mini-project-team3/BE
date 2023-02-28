package com.sparta.be.common;

public class ResponseUtils {

    public static <T> SuccessResponseDto<T> ok(T data) {
        return new SuccessResponseDto<T>(true, data);
    }


    public static <T>FailureResponseDto<T> error(ErrorResponse error) {
        return new FailureResponseDto<T>(false, error);
    }

    public static <T> SuccessResponseDto<T> ok() {
        return new SuccessResponseDto<T>(true, null);
    }
}
