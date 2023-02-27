package com.sparta.be.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "아이디는 4~10자리 영문 소문자(a~z),숫자(0~9)를 사용하세요.")
    private String loginId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$%^&*()_+=?,./<>{}\\[\\]\\-]{8,15}$", message = "비밀번호는 8~15자리 영문 대소문자(a~z, A~Z), 숫자(0~9), 특수문자를 사용하세요.")
    private String password;
    private String nickname;
}
