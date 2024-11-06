package com.example.crudpractice.order.api.dto.request;

public record OrderCreateReqDto(
    String name,
    int count
) {
}
