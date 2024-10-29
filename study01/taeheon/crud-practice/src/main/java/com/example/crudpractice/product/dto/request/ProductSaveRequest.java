package com.example.crudpractice.product.dto.request;

import com.example.crudpractice.product.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductSaveRequest {

    @NotBlank
    private String name;
    @NotNull
    private int quantity;
    @NotNull
    private int price;

    public Product toEntity(ProductSaveRequest requestDto) {
        return Product.builder()
                .name(requestDto.name)
                .quantity(requestDto.quantity)
                .price(requestDto.price)
                .creatAt(LocalDateTime.now())
                .build();
    }
}
