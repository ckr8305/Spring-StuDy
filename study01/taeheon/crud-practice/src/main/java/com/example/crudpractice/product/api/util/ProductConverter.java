package com.example.crudpractice.product.api.util;

import com.example.crudpractice.product.api.dto.request.ProductSaveRequest;
import com.example.crudpractice.product.api.dto.response.ProductInfoResponse;
import com.example.crudpractice.product.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toEntity(ProductSaveRequest requestDto) {
        return Product.builder()
                .name(requestDto.name())
                .quantity(requestDto.quantity())
                .price(requestDto.price())
                .creatAt(LocalDateTime.now())
                .build();
    }

    public static ProductInfoResponse toDTO(Product product) {
        return new ProductInfoResponse(product.getName(), product.getQuantity(), product.getPrice());
    }
}
