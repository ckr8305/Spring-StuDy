package com.example.crudpractice.order.api.dto.response;

public record OrderResDto(
    String memberName,
    String productName,
    int totalCount,
    int totalAmount
) {

}
