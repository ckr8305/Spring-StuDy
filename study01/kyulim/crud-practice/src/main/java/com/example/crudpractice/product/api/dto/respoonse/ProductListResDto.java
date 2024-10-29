package com.example.crudpractice.product.api.dto.respoonse;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductListResDto(
        List<ProductInfoResDto> products
) {
    public static ProductListResDto from(List<ProductInfoResDto> products) {
        return ProductListResDto.builder()
                .products(products)
                .build();
    }
}
