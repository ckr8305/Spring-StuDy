package com.example.crudpractice.product.api.dto.reqeust;

import com.example.crudpractice.product.domain.Product;
import lombok.Builder;

@Builder
public record ProductUpdateReqDto(
    String name,
    int quantity,
    int price
) {
    public Product toEntity(ProductUpdateReqDto productReqDtoDto) {
        return Product.builder()
                .name(productReqDtoDto.name())
                .quantity(productReqDtoDto.quantity())
                .price(productReqDtoDto.price())
                .build();
    }
}
