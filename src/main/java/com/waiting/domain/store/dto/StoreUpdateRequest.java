package com.waiting.domain.store.dto;

import lombok.Getter;

@Getter
// 매장 수정 요청 DTO
public class StoreUpdateRequest {

    private String storeName;
    private String address;
    private String phone;
    private String openTime;
    private String closeTime;
    private Integer maxWaiting;
    private Integer avgServiceTime;
}