package com.waiting.waiting_system.repository;

import com.waiting.waiting_system.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}