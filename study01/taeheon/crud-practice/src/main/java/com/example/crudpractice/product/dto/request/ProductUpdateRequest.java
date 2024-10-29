package com.example.crudpractice.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotBlank
    private String name;
    @NotNull
    private int quantity;
    @NotNull
    private int price;

    @Builder
    public ProductUpdateRequest(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
