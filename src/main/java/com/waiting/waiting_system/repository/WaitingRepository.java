package com.waiting.waiting_system.repository;

import com.waiting.waiting_system.entity.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
}