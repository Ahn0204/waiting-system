package com.waiting.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인용
    Optional<User> findByLoginId(String loginId);

    // 전화번호 중복 체크
    boolean existsByPhone(String phone);

    // 로그인 ID 중복 체크
    boolean existsByLoginId(String loginId);
}