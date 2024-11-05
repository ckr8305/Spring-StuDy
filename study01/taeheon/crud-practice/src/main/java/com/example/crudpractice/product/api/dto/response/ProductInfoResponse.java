package com.example.crudpractice.product.api.dto.response;

public record ProductInfoResponse(
        String name,
        int quantity,
        int price
) {
}
