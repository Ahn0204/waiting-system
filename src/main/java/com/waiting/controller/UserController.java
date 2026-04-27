package com.waiting.controller;

import com.waiting.domain.user.dto.UserLoginRequest;
import com.waiting.domain.user.dto.UserSignupRequest;
import com.waiting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/signup")
    public String signup(@RequestBody UserSignupRequest request) {
        userService.signup(request);
        return "회원가입 성공";
    }

    // 로그인 API
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }
}