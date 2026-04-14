package com.waiting.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 특정 유저 예약 목록
    List<Reservation> findByUser_UserId(Long userId);

    // 특정 매장 예약 목록
    List<Reservation> findByStore_StoreId(Long storeId);
} 