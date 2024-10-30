package com.example.crudpractice.order.api.dto.response;

import jakarta.validation.constraints.NotNull;

public record OrderInfoResponse(
        @NotNull
        Long MemberId,
        @NotNull
        Long OrderId,
        @NotNull
        OrderProductResponse orderProducts
) {

}
