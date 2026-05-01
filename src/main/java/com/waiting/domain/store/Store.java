package com.waiting.domain.store;

import com.waiting.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Store {

    // 매장 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    // 매장 사장 (User 엔티티와 연관)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    // 매장 이름
    @Column(nullable = false)
    private String storeName;

    // 매장 주소
    @Column(nullable = false)
    private String address;

    // 매장 전화번호 (유니크)
    @Column(unique = true)
    private String phone;

    // 매장 영업 시작 시간
    private LocalTime openTime;
    // 매장 영업 종료 시간
    private LocalTime closeTime;

    // 대기 시스템 활성화 여부 (Y/N)
    @Column(length = 1)
    private String waitingEnabled = "Y";

    // 최대 대기 인원 수
    private int maxWaiting = 50;
    // 평균 서비스 시간 (분 단위)
    private int avgServiceTime = 15;

    // 매장 생성 및 수정 시간
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 매장 등록 시 createdAt 자동 설정
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // 매장 수정 시 updatedAt 자동 설정
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}