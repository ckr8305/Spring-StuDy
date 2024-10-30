package com.example.crudpractice.product.api.dto.response;

import com.example.crudpractice.product.domain.Product;

public record ProductInfoResponse(
        String name,
        int quantity,
        int price
) {
    public static ProductInfoResponse from(Product product) {
        return new ProductInfoResponse(product.getName(), product.getQuantity(), product.getPrice());
    }
}
