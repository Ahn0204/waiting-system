package com.waiting.service;

import com.waiting.domain.store.Store;
import com.waiting.domain.store.StoreRepository;
import com.waiting.domain.user.User;
import com.waiting.domain.user.UserRepository;
import com.waiting.domain.waiting.Waiting;
import com.waiting.domain.waiting.WaitingRepository;
import com.waiting.domain.waiting.dto.WaitingCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WaitingService {

    private final WaitingRepository waitingRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 웨이팅 등록
    @Transactional
    public String createWaiting(WaitingCreateRequest request) {

        // 1. 유저 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        // 2. 매장 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("매장 없음"));

        // 3. 마지막 순번 조회
        Waiting lastWaiting = waitingRepository
                .findTopByStore_StoreIdOrderByWaitingNumberDesc(store.getStoreId());

        int nextNumber = (lastWaiting == null) ? 1 : lastWaiting.getWaitingNumber() + 1;

        // 4. 웨이팅 생성
        Waiting waiting = new Waiting();
        waiting.setUser(user);
        waiting.setStore(store);
        waiting.setWaitingNumber(nextNumber);
        waiting.setPeopleCount(request.getPeopleCount());
        waiting.setStatus("WAIT");

        waitingRepository.save(waiting);

        return "웨이팅 등록 완료 (번호: " + nextNumber + ")";
    }
}