package com.sparta.be.exception;

import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.FailureResponseDto;
import com.sparta.be.common.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<FailureResponseDto<ErrorResponse>> illegalArgumentException(IllegalArgumentException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        log.error(response.getMessage());
        return ResponseEntity.badRequest().body(ResponseUtils.error(response));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailureResponseDto<ErrorResponse>> methodValidException(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.of(e.getBindingResult());
        log.error(response.getMessage());
        return ResponseEntity.badRequest().body(ResponseUtils.error(response));
    }

}
