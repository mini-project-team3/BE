package com.sparta.be.dto;

import lombok.Getter;

@Getter
public class UserRequestDto { // 회원가입 및 로그인 할때 받아올 값
        private String loginId;
        private String password;
        private String nickName;
}
