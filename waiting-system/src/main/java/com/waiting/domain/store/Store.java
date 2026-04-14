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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String address;

    @Column(unique = true)
    private String phone;

    private LocalTime openTime;
    private LocalTime closeTime;

    @Column(length = 1)
    private String waitingEnabled = "Y";

    private int maxWaiting = 50;
    private int avgServiceTime = 15;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}