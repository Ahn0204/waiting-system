package com.waiting.controller;

import com.waiting.domain.store.Store;
import com.waiting.domain.store.dto.StoreCreateRequest;
import com.waiting.service.StoreService;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    // 사용자가 매장 목록 조회 API
    @GetMapping
    public List<Store> getStoreList() {
        return storeService.getStoreList();
    }

    // 사장이 본인의 매장 목록 조회 API
    @GetMapping("/owner/{userId}")
    public List<Store> getMyStores(@PathVariable("userId") Long userId) {
        return storeService.getMyStores(userId);
    }
}