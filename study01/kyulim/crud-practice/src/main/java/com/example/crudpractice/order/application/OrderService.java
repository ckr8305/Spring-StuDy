package com.example.crudpractice.order.application;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.member.domain.repository.MemberRepository;
import com.example.crudpractice.order.api.dto.response.OrderResDto;
import com.example.crudpractice.order.domain.Order;
import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.order.domain.repository.OrderProductRepository;
import com.example.crudpractice.order.domain.repository.OrderRepository;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public void createOrder(long memberId, String name, int count) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Product product = productRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 상품 수량 검증
        if (product.getQuantity() < count)
            throw new IllegalArgumentException("상품 수량이 부족합니다.");
        product.decreaseQuantity(count);

        // 주문 생성
        Order order = Order.builder()
            .member(member)
            .createAt(LocalDateTime.now())
            .build();

        // 주문 상품 생성
        OrderProduct orderProduct = OrderProduct.builder()
            .order(order)
            .product(product)
            .totalCount(count)
            .totalAmount(product.getPrice() * count)
            .build();

        order.addOrderProduct(orderProduct);

        orderRepository.save(order);
    }

    public List<OrderResDto> getOrderDetail(long memberId) {
        List<Order> orders = orderRepository.findByMemberMemberId(memberId);
        return orders.stream()
            .flatMap(order -> order.getOrderProductList().stream()
                .map(orderProduct -> new OrderResDto(
                    order.getMember().getName(),
                    orderProduct.getProduct().getName(),
                    orderProduct.getTotalCount(),
                    orderProduct.getTotalAmount()
                ))).toList();
    }

    @Transactional
    public void deleteOrder(long orderId) {
        // 주문 조회
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> {
                return new IllegalArgumentException("주문을 찾을 수 없습니다.");
            });

        for (OrderProduct orderProduct : order.getOrderProductList()) {

            // 주문상품에 포함된 상품(Product)의 수량을 회복
            Product product = orderProduct.getProduct();
            product.increaseQuantity(orderProduct.getTotalCount());
            productRepository.save(product);  // 상품 수량 업데이트
            // OrderProduct 삭제
            orderProductRepository.delete(orderProduct);
        }

        // 주문 삭제
        orderRepository.delete(order);
    }

}
