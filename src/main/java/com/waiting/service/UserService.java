package com.waiting.service;

import com.waiting.domain.user.User;
import com.waiting.domain.user.UserRepository;
import com.waiting.domain.user.dto.UserLoginRequest;
import com.waiting.domain.user.dto.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입 기능
    @Transactional
    public void signup(UserSignupRequest request) {

        User user = new User();
        user.setLoginId(request.getLoginId());
        user.setPassword(request.getPassword()); // 암호화 예정
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setRole("USER");

        userRepository.save(user);
    }

    // 로그인 기능 (추후 JWT 토큰 발급 등으로 확장 가능)
    @Transactional(readOnly = true)
    public String login(UserLoginRequest request) {

        // 1. 사용자 조회
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        // 2. 비밀번호 비교 (지금은 평문 비교)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 3. 로그인 성공
        return "로그인 성공";
    }
}