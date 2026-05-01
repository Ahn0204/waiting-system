package com.waiting.service;

import com.waiting.domain.store.Store;
import com.waiting.domain.store.StoreRepository;
import com.waiting.domain.store.dto.StoreCreateRequest;
import com.waiting.domain.user.User;
import com.waiting.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 매장 등록
    @Transactional
    public String createStore(StoreCreateRequest request) {

        // 1. 사장 유저 조회
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        // 2. 매장 생성
        Store store = new Store();
        store.setOwner(owner);
        store.setStoreName(request.getStoreName());
        store.setAddress(request.getAddress());
        store.setPhone(request.getPhone());
        store.setOpenTime(LocalTime.parse(request.getOpenTime()));
        store.setCloseTime(LocalTime.parse(request.getCloseTime()));

        storeRepository.save(store);

        return "매장 등록 완료";
    }
}