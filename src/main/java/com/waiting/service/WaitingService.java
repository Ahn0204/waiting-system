package com.waiting.service;

import com.waiting.domain.store.Store;
import com.waiting.domain.store.StoreRepository;
import com.waiting.domain.user.User;
import com.waiting.domain.user.UserRepository;
import com.waiting.domain.waiting.Waiting;
import com.waiting.domain.waiting.WaitingRepository;
import com.waiting.domain.waiting.dto.WaitingCreateRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    // 내 웨이팅 조회
    @Transactional(readOnly = true)
    public List<Waiting> getMyWaiting(Long userId) {
        return waitingRepository.findByUser_UserId(userId);
    }

    // 웨이팅 취소
    @Transactional
    public String cancelWaiting(Long waitingId) {

        // 1. 웨이팅 조회
        Waiting waiting = waitingRepository.findById(waitingId)
                .orElseThrow(() -> new IllegalArgumentException("웨이팅 없음"));

        // 2. 상태 변경
        waiting.setStatus("CANCEL");

        return "웨이팅 취소 완료";
    }

    // 매장 관리자 -> 유저 웨이팅 상태 변경
    // 웨이팅 상태 변경 (예: CALL, ENTER)
    @Transactional
    public String updateStatus(Long waitingId, String status) {

        // 1. 웨이팅 조회
        Waiting waiting = waitingRepository.findById(waitingId)
                .orElseThrow(() -> new IllegalArgumentException("웨이팅 없음"));

        // 2. 상태 변경
        waiting.setStatus(status);

        // 3. 시간 기록
        if ("CALL".equals(status)) {
            waiting.setCalledAt(java.time.LocalDateTime.now());
        }

        if ("ENTER".equals(status)) {
            waiting.setEnteredAt(java.time.LocalDateTime.now());
        }

        return "상태 변경 완료: " + status;
    }
}