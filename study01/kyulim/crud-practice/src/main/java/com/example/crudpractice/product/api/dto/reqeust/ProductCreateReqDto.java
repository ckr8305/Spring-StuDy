package com.example.crudpractice.product.api.dto.reqeust;

import com.example.crudpractice.product.domain.Product;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ProductCreateReqDto(
    String name,
    int quantity,
    int price,
    LocalDateTime createAt
) {
    public Product toEntity(ProductCreateReqDto productReqDtoDto) {
        return Product.builder()
                .name(productReqDtoDto.name())
                .quantity(productReqDtoDto.quantity())
                .price(productReqDtoDto.price())
                .createAt(LocalDateTime.now())
                .build();
    }
}
