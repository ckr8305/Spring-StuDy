package com.example.crudpractice.product.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductSaveRequest(
        @NotBlank
        String name,
        @NotNull
        int quantity,
        @NotNull
        int price
) {
}
