package com.example.crudpractice.order.api.dto.response;

import jakarta.validation.constraints.NotNull;

public record OrderProductResponse(
        @NotNull
        Long productId,
        @NotNull
        int totalCount,
        @NotNull
        int totalAmount
) {

}
