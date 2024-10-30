package com.example.crudpractice.product.api.dto.request;

import com.example.crudpractice.product.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductSaveRequest(
        @NotBlank
        String name,
        @NotNull
        int quantity,
        @NotNull
        int price
) {
    public Product toEntity(ProductSaveRequest requestDto) {
        return Product.builder()
                .name(requestDto.name)
                .quantity(requestDto.quantity)
                .price(requestDto.price)
                .creatAt(LocalDateTime.now())
                .build();
    }
}
