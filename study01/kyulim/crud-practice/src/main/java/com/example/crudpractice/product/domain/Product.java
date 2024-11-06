package com.example.crudpractice.product.domain;

import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.product.api.dto.reqeust.ProductUpdateReqDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    private String name;
    private int quantity;
    private int price;
    private LocalDateTime createAt;

    @OneToMany
    List<OrderProduct> orderProductList;

    @Builder
    public Product(String name, int quantity, int price, LocalDateTime createAt) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.createAt = createAt;
    }

    public void update(ProductUpdateReqDto productUpdateReqDto) {
        this.name = productUpdateReqDto.name();
        this.quantity = productUpdateReqDto.quantity();
        this.price = productUpdateReqDto.price();
    }

    public void decreaseQuantity(int count) {
        if (this.quantity < count)
            throw new IllegalArgumentException("상품 수량이 부족합니다.");
        this.quantity -= count;
    }

    public void increaseQuantity(int count) {
        this.quantity += count;
    }
}
