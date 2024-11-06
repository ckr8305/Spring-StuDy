package com.example.crudpractice.order.api.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderSaveRequest(
        @NotNull
        List<Long> productIds,
        @NotNull
        List<Integer> counts
) {
}
