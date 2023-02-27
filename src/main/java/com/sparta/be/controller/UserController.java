package com.sparta.be.controller;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.dto.LoginRequestDto;
import com.sparta.be.dto.SignupRequestDto;
import com.sparta.be.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ApiResponseDto<Void> signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public ApiResponseDto<Void> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
