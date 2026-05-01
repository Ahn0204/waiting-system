package com.waiting.service;

import com.waiting.domain.store.Store;
import com.waiting.domain.store.StoreRepository;
import com.waiting.domain.store.dto.StoreCreateRequest;
import com.waiting.domain.store.dto.StoreUpdateRequest;
import com.waiting.domain.user.User;
import com.waiting.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

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

    // 사용자가 매장 목록 조회
    @Transactional(readOnly = true)
    public List<Store> getStoreList() {
        return storeRepository.findAll()
                .stream()
                .filter(store -> "ACTIVE".equals(store.getStatus()))
                .toList();
    }

    // 사장이 본인의 매장 목록 조회
    @Transactional(readOnly = true)
    public List<Store> getMyStores(Long userId) {
        return storeRepository.findByOwner_UserId(userId)
                .stream()
                .filter(store -> "ACTIVE".equals(store.getStatus()))
                .toList();
    }

    // 매장 상세 조회
    @Transactional(readOnly = true)
    public Store getStoreDetail(Long storeId) {

        return storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("매장 없음"));
    }

    // 매장 수정
    @Transactional
    public String updateStore(Long storeId, StoreUpdateRequest request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("매장 없음"));
        // 매장 이름
        if (request.getStoreName() != null) {
            store.setStoreName(request.getStoreName());
        }
        // 매장 주소
        if (request.getAddress() != null) {
            store.setAddress(request.getAddress());
        }
        // 매장 전화번호
        if (request.getPhone() != null) {
            store.setPhone(request.getPhone());
        }
        // 매장 영업 시작 시간
        if (request.getOpenTime() != null) {
            store.setOpenTime(java.time.LocalTime.parse(request.getOpenTime()));
        }
        // 매장 영업 종료 시간
        if (request.getCloseTime() != null) {
            store.setCloseTime(java.time.LocalTime.parse(request.getCloseTime()));
        }
        // 최대 대기 인원 수
        if (request.getMaxWaiting() != null) {
            store.setMaxWaiting(request.getMaxWaiting());
        }
        // 평균 서비스 시간
        if (request.getAvgServiceTime() != null) {
            store.setAvgServiceTime(request.getAvgServiceTime());
        }

        return "매장 수정 완료";
    }

    // 매장 삭제 (소프트 삭제 : 비활성화)
    @Transactional
    public String deleteStore(Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("매장 없음"));

        store.setStatus("INACTIVE");

        return "매장 삭제 완료 (비활성화)";
    }

    // 매장 복구 (비활성화 -> 활성화)
    @Transactional
    public String restoreStore(Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("매장 없음"));

        store.setStatus("ACTIVE");

        return "매장 복구 완료";
    }

}