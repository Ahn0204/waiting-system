package com.waiting.waiting_system.service;

import com.waiting.waiting_system.entity.Store;
import com.waiting.waiting_system.entity.User;
import com.waiting.waiting_system.entity.Waiting;
import com.waiting.waiting_system.repository.StoreRepository;
import com.waiting.waiting_system.repository.UserRepository;
import com.waiting.waiting_system.repository.WaitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WaitingService {

    private final WaitingRepository waitingRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 웨이팅 등록
    public Waiting createWaiting(Long userId, Long storeId, int partySize) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("매장 없음"));

        Waiting waiting = new Waiting();

        //waiting.setUser(user);
        //waiting.setStore(store);
       // waiting.setPartySize(partySize);
        //waiting.setStatus(Waiting.Status.WAITING);

        // 지금은 간단하게 random (내일 제대로 구현)
        //waiting.setWaitingNumber((int)(Math.random() * 1000));

        return waitingRepository.save(waiting);
    }
}