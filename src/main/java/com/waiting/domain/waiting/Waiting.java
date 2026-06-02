package com.waiting.domain.waiting;

import com.waiting.domain.user.User;
import com.waiting.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Waiting {

    // 웨이팅 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waitingId;

    // 웨이팅을 등록한 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 웨이팅 등록된 매장
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    // 매장별 대기 순번
    private int waitingNumber;

    /**
     * 웨이팅 상태
     * WAIT : 대기중
     * CALLED : 호출됨
     * ENTERED : 입장완료
     * CANCELED : 취소
     */
    private String status = "WAIT";

    // 방문 인원 수
    private int peopleCount = 1;

    // 점주가 호출한 시간
    private LocalDateTime calledAt;

    // 고객이 입장한 시간
    private LocalDateTime enteredAt;

    // 웨이팅 생성 시간
    private LocalDateTime createdAt;

    // 엔티티 최초 저장 시 자동 생성
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}