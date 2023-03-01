package com.sparta.be.entity;

import com.sparta.be.dto.SignupRequestDto;
import com.sparta.be.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    @Mock
    UserService userService;

    @Nested
    @DisplayName("사용자 객체 생성")
    class CreateUser {

        private String loginId;
        private String password;
        private String nickname;

        @BeforeEach
        void setup() {
            loginId = "user1";
            password = "Password1!";
            nickname = "닉네임1";
        }

        @Test
        @DisplayName("정상 케이스")
        void createUser_Normal() {
            // Given
            SignupRequestDto requestDto = SignupRequestDto.of(loginId, password, nickname);

            // When
            User user = new User(requestDto.getLoginId(), requestDto.getPassword(), requestDto.getNickname());

            // Then
            assertNull(user.getId());
            assertEquals(loginId, user.getLoginId());
            assertEquals(password, user.getPassword());
            assertEquals(nickname, user.getNickname());
        }


    }

}