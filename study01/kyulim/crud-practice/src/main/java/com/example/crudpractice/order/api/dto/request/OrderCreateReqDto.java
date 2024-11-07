package com.example.crudpractice.order.api.dto.request;

public record OrderCreateReqDto(
    long memberId,
    String name,
    int count
) {
}
