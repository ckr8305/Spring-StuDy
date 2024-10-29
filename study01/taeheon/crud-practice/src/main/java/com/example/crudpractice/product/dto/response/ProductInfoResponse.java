package com.example.crudpractice.product.dto.response;

import com.example.crudpractice.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductInfoResponse {

    private String name;
    private int quantity;
    private int price;

    public static ProductInfoResponse from(Product product) {
        return new ProductInfoResponse(product.getName(), product.getQuantity(), product.getPrice());
    }
}
