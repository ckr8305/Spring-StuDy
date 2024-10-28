package com.example.crudpractice.product.api.dto.respoonse;

import com.example.crudpractice.product.domain.Product;
import lombok.Builder;

@Builder
public record ProductInfoResDto(
        String name,
        int quantity,
        int price
) {
    public static ProductInfoResDto from(Product product) {
        return ProductInfoResDto.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}
