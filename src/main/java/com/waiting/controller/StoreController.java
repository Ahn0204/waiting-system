package com.waiting.controller;

import com.waiting.domain.store.dto.StoreCreateRequest;
import com.waiting.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
// 매장 관련 API 컨트롤러
public class StoreController {

    private final StoreService storeService;

    // 매장 등록 API
    @PostMapping
    public String createStore(@RequestBody StoreCreateRequest request) {
        return storeService.createStore(request);
    }
}