package com.example.crudpractice.order.application;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.member.domain.repository.MemberRepository;
import com.example.crudpractice.order.api.dto.request.OrderCreateReqDto;
import com.example.crudpractice.order.domain.Order;
import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.order.domain.repository.OrderProductRepository;
import com.example.crudpractice.order.domain.repository.OrderRepository;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void order(long memberId, String name, int count) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Product product = productRepository.findByName(name);

        // 상품 수량 검증
        if (product.getQuantity() < count)
            throw new IllegalArgumentException("상품 수량이 부족합니다.");
        product.decreaseQuantity(count);

        // 주문 상품 생성
        OrderProduct orderProduct = OrderProduct.builder()
            .product(product)
            .totalCount(count)
            .totalAmount(product.getPrice() * count)
            .build();

        List<OrderProduct> orderProductList = new ArrayList<>();
        orderProductList.add(orderProduct);

        // 주문 생성
        Order order = Order.builder()
            .member(member)
            .createAt(LocalDateTime.now())
            .orderProductList(orderProductList)
            .build();

        orderRepository.save(order);
    }
}
