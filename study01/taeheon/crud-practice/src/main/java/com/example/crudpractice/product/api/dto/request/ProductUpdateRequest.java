package com.example.crudpractice.product.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record ProductUpdateRequest(
        @NotBlank
        String name,
        @NotNull
        int quantity,
        @NotNull
        int price
) {
    @Builder
    public ProductUpdateRequest(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
