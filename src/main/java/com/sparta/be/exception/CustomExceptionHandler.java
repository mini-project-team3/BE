package com.sparta.be.exception;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDto<ErrorResponse>> illegalArgumentException(IllegalArgumentException e, HttpRequest request) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        System.out.println(request.getURI());
        log.error(response.getMessage());
        return ResponseEntity.badRequest().body(ResponseUtils.error(response));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<ErrorResponse>> methodValidException(MethodArgumentNotValidException e, HttpRequest request) {
        ErrorResponse response = ErrorResponse.of(e.getBindingResult());
        System.out.println(request.getURI());
        log.error(response.getMessage());
        return ResponseEntity.badRequest().body(ResponseUtils.error(response));
    }

}
