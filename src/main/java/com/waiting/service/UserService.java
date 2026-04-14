package com.waiting.service;

import com.waiting.domain.user.User;
import com.waiting.domain.user.UserRepository;
import com.waiting.domain.user.dto.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}