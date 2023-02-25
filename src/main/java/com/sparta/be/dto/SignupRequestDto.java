package com.sparta.be.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "유저 이름은 4~10자 영문 대 소문자, 숫자를 사용하세요.")
    private String loginId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$%^&*()_+=?,./<>{}\\[\\]\\-]{8,15}$", message = "비밀번호는 8~15자 영문 대 소문자, 숫자를 사용하세요.")
    private String password;
    private String nickname;
}
