package com.example.crudpractice.order.api.dto.request;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.order.domain.Order;
import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.product.domain.Product;
import java.time.LocalDateTime;

public record OrderCreateReqDto(
    long memberId,
    String name,
    int count
) {
    public Order toOrder(Member member) {
        return Order.builder()
            .member(member)
            .createAt(LocalDateTime.now())
            .build();
    }

    public OrderProduct toOrderProduct(Order order, Product product) {
        return OrderProduct.builder()
            .order(order)
            .product(product)
            .totalCount(count)
            .totalAmount(product.getPrice() * count)
            .build();
    }
}
