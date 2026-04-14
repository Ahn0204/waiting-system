package com.waiting.domain.store;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // 특정 사용자(사장)의 매장 목록
    List<Store> findByOwner_UserId(Long userId);
}