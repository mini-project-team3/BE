package com.sparta.be.security;

import com.sparta.be.common.ErrorType;
import com.sparta.be.entity.User;
import com.sparta.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nikcname) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(nikcname)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorType.NOT_FOUND_USER.getMessage()));   // 사용자가 DB 에 없으면 예외처리

        return new UserDetailsImpl(user, user.getNickname());   // 사용자 정보를 UserDetails 로 반환
    }

}
