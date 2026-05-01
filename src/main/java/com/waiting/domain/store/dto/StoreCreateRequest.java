package com.waiting.domain.store.dto;

import lombok.Getter;

@Getter
// 매장 등록 요청 DTO
public class StoreCreateRequest {

    private Long ownerId;
    private String storeName;
    private String address;
    private String phone;
    private String openTime;
    private String closeTime;
}