package com.sparta.be.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String loginId;
    private String password;
    private String nickName;
}
