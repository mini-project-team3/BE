package com.sparta.be.service;

import com.sparta.be.common.ErrorType;
import com.sparta.be.common.SuccessResponseDto;
import com.sparta.be.dto.SignupRequestDto;
import com.sparta.be.entity.User;
import com.sparta.be.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Mock
    User user;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입 - 정상")
    void signup_success() {
        // Given
        SignupRequestDto requestDto = createSignupRequestDto();

        when(userRepository.findByLoginId(requestDto.getLoginId())).thenReturn(Optional.empty());
        when(userRepository.findByNickname(requestDto.getNickname())).thenReturn(Optional.empty());

        // When
        SuccessResponseDto<Void> result = userService.signup(requestDto);

        // Then
        assertThat(result.isSuccess()).isTrue();
        then(userRepository).should().findByLoginId(requestDto.getLoginId());
        then(userRepository).should().findByNickname(requestDto.getNickname());
        then(userRepository).should().save(any(User.class));
    }

    @Nested
    @DisplayName("회원 가입 - 실패")
    class FailCases {
        @Test
        @DisplayName("중복된 ID인 경우")
        void givenDuplicatedLoginId_whenSignup_thenThrowsException() {
            // Given
            SignupRequestDto requestDto = createSignupRequestDto();

            when(userRepository.findByLoginId(requestDto.getLoginId())).thenReturn(Optional.of(User.builder().build()));

            // When
            Throwable t = catchThrowable(() -> userService.signup(requestDto));

            // Then
            assertThat(t)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorType.DUPLICATED_USERNAME.getMessage());
            then(userRepository).should().findByLoginId(requestDto.getLoginId());
        }

        @Test
        @DisplayName("중복된 nickname 인 경우")
        void givenDuplicatedNickname_whenSignup_thenThrowsException() {
            // Given
            SignupRequestDto requestDto = createSignupRequestDto();

            when(userRepository.findByNickname(requestDto.getNickname())).thenReturn(Optional.of(User.builder().build()));

            // When
            Throwable t = catchThrowable(() -> userService.signup(requestDto));

            // Then
            assertThat(t)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorType.DUPLICATED_NICKNAME.getMessage());
            then(userRepository).should().findByLoginId(requestDto.getLoginId());
            then(userRepository).should().findByNickname(requestDto.getNickname());
        }
    }

    private SignupRequestDto createSignupRequestDto() {
        return SignupRequestDto.of("user1", "Password1!", "닉네임1");
    }

}
