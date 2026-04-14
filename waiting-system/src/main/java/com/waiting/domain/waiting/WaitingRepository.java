package com.waiting.domain.waiting;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {

    // 특정 매장의 현재 웨이팅 목록
    List<Waiting> findByStore_StoreIdAndStatusOrderByWaitingNumberAsc(Long storeId, String status);

    // 특정 유저의 웨이팅 조회
    List<Waiting> findByUser_UserId(Long userId);

    // 특정 매장의 마지막 웨이팅 번호
    Waiting findTopByStore_StoreIdOrderByWaitingNumberDesc(Long storeId);
}