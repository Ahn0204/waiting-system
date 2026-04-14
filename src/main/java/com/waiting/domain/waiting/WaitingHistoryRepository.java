package com.waiting.domain.waiting;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WaitingHistoryRepository extends JpaRepository<WaitingHistory, Long> {

    List<WaitingHistory> findByWaiting_WaitingId(Long waitingId);
}