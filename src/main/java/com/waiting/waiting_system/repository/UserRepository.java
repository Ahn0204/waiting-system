package com.waiting.waiting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waiting.waiting_system.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
