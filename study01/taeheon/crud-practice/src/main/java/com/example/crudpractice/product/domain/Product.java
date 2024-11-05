package com.example.crudpractice.product.domain;

import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.product.api.dto.request.ProductUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creatAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    public Product(String name, int quantity, int price, LocalDateTime creatAt) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.creatAt = creatAt;
    }

    public void update(ProductUpdateRequest requestDto) {
        this.name = requestDto.name();
        this.quantity = requestDto.quantity();
        this.price = requestDto.price();
    }

    public void reduceProductQuantity(int count) {
        if (this.quantity - count < 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.quantity -= count;
    }
}
