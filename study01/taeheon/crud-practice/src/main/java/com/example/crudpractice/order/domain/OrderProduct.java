package com.example.crudpractice.order.domain;

import com.example.crudpractice.product.domain.Product;
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
    @Column(name = "order_product_id", nullable = false)
    private Long orderProductId;

    @Column(nullable = false)
    private int totalCount;
    @Column(nullable = false)
    private int totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 주문 상품

    @Builder
    public OrderProduct(Product product, Order order, int totalCount, int totalAmount) {
        this.product = product;
        this.order = order;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
    }
}
