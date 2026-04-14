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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waitingId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private int waitingNumber;

    private String status = "WAIT";

    private int peopleCount = 1;

    private LocalDateTime calledAt;
    private LocalDateTime enteredAt;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}