package com.waiting.controller;

import com.waiting.domain.waiting.Waiting;
import com.waiting.domain.waiting.dto.WaitingCreateRequest;
import com.waiting.service.WaitingService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/waiting")
public class WaitingController {

    private final WaitingService waitingService;

    // 웨이팅 등록
    @PostMapping
    public String createWaiting(@RequestBody WaitingCreateRequest request) {
        return waitingService.createWaiting(request);
    }

    // 내 웨이팅 조회
    @GetMapping("/user/{userId}")
    public List<Waiting> getMyWaiting(@PathVariable("userId") Long userId) {
        return waitingService.getMyWaiting(userId);
    }
}