package com.sparta.be.security;

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
        public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
            User user = userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없습니다."));

            return new UserDetailsImpl(user, user.getLoginId());
    }


}
