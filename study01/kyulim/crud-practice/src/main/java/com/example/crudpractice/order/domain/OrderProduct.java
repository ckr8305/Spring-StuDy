package com.example.crudpractice.order.domain;

import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private long orderProductId;

    private int totalAmount;
    private int totalCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    @Builder
    public OrderProduct(int totalAmount, int totalCount, Order order, Product product) {
        this.totalAmount = totalAmount;
        this.totalCount = totalCount;
        this.order = order;
        this.product = product;
    }
}
