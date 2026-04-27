package com.waiting.domain.waiting.dto;

import lombok.Getter;

@Getter
// 웨이팅 등록 요청 DTO
public class WaitingCreateRequest {

    private Long userId;
    private Long storeId;
    private int peopleCount;
}