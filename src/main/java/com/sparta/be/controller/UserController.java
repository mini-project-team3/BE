package com.sparta.be.controller;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.dto.LoginRequestDto;
import com.sparta.be.dto.SignupRequestDto;
import com.sparta.be.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponseDto<?> signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
           return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "로그인 조건이 맞지 않습니다."));
        }

        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public ApiResponseDto<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
