package com.example.crudpractice.order.api.util;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.order.api.dto.request.OrderSaveRequest;
import com.example.crudpractice.order.api.dto.response.OrderInfoResponse;
import com.example.crudpractice.order.api.dto.response.OrderProductResponse;
import com.example.crudpractice.order.domain.Order;
import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.product.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {

    public static Order toOrderEntity(OrderSaveRequest request, Member member) {
        return Order.builder()
                .member(member)
                .build();
    }

    public static OrderProduct toOrderProductEntity(Order order, Product product, int count) {
        return OrderProduct.builder()
                .product(product)
                .order(order)
                .totalCount(count)
                .totalAmount(product.getPrice())
                .build();
    }

    public static OrderInfoResponse toOrderDTO(OrderProduct orderProduct) {
        return new OrderInfoResponse(
                orderProduct.getOrder().getOrderId(),
                new OrderProductResponse(
                        orderProduct.getProduct().getProductId(),
                        orderProduct.getTotalCount(),
                        orderProduct.getTotalAmount() * orderProduct.getTotalCount()
                )
        );
    }
}
