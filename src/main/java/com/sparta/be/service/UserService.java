package com.sparta.be.service;

import com.sparta.be.common.SuccessResponseDto;
import com.sparta.be.common.ErrorType;
import com.sparta.be.common.ResponseUtils;
import com.sparta.be.dto.LoginRequestDto;
import com.sparta.be.dto.SignupRequestDto;
import com.sparta.be.entity.User;
import com.sparta.be.jwt.JwtUtil;
import com.sparta.be.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
    public SuccessResponseDto<Void> signup(SignupRequestDto signupRequestDto) { // 회원 가입
        String loginId = signupRequestDto.getLoginId();
        String password = passwordEncoder.encode(signupRequestDto.getPassword()); // 비밀번호 암호화
        String nickname = signupRequestDto.getNickname();

        //회원 중복 확인
        Optional<User> found = userRepository.findByLoginId(loginId);
        if (found.isPresent()) {
            throw new IllegalArgumentException(ErrorType.DUPLICATED_USERNAME.getMessage());
        }

        Optional<User> nickNameFound = userRepository.findByNickname(nickname);
        if(nickNameFound.isPresent()){
            throw new IllegalArgumentException(ErrorType.DUPLICATED_NICKNAME.getMessage());
        }

        User user = new User(loginId, password, nickname);
        userRepository.save(user);
        return ResponseUtils.ok();

    }

    @Transactional(readOnly = true)
    public SuccessResponseDto<Void> login(LoginRequestDto loginRequestDto, HttpServletResponse response) { // 회원 가입
        String loginId = loginRequestDto.getLoginId();
        String password = loginRequestDto.getPassword();

        //사용자 확인
        if (userRepository.findByLoginId(loginId).isEmpty()) {
            throw new IllegalArgumentException(ErrorType.NOT_FOUND_USER.getMessage());
        }

        //비밀번호 중복 확인
        User user = userRepository.findByLoginId(loginId).get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException(ErrorType.NOT_MATCHING_PASSWORD.getMessage());
        }

        // Authorization 에 token 설정
        String token = jwtUtil.createToken(user.getNickname());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        // 쿠키 설정
        Cookie cookie = new Cookie("token", token.substring(7));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return ResponseUtils.ok();

    }
}
