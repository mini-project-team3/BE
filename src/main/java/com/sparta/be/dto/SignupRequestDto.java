package com.sparta.be.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String loginId;
    private String password;
    private String nickname;
}
