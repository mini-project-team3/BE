package com.sparta.be.service;

import com.sparta.be.common.ApiResponseDto;
import com.sparta.be.common.ErrorResponse;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.dto.LoginRequestDto;
import com.sparta.be.dto.SignupRequestDto;
import com.sparta.be.entity.User;
import com.sparta.be.jwt.JwtUtil;
import com.sparta.be.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ApiResponseDto<?> signup(SignupRequestDto signupRequestDto) { // 회원 가입
        String loginId = signupRequestDto.getLoginId();
        String password = passwordEncoder.encode(signupRequestDto.getPassword()); // 비밀번호 암호화
        String nickname = signupRequestDto.getNickname();

        //회원 중복 확인
        Optional<User> found = userRepository.findByLoginId(loginId);
        if (found.isPresent()) {
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "중복된 사용자 입니다."));
        }

        User user = new User(loginId, password, nickname);
        userRepository.save(user);
        return ResponseUtils.ok();

    }

    @Transactional
    public ApiResponseDto<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) { // 회원 가입
        String loginId = loginRequestDto.getLoginId();
        String password = loginRequestDto.getPassword();

        //사용자 확인
        if (userRepository.findByLoginId(loginId).isEmpty()) {
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "등록된 사용자가 없습니다."));
        }

        //비밀번호 중복 확인
        User user = userRepository.findByLoginId(loginId).get();
        if (!passwordEncoder.matches(password, user.getPassword())){
            return ResponseUtils.error(ErrorResponse.of(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."));
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginId()));
        return ResponseUtils.ok();

    }
}
