package com.example.crudpractice.order.application;

import com.example.crudpractice.global.util.Check;
import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.order.api.dto.request.OrderCreateReqDto;
import com.example.crudpractice.order.api.dto.response.OrderResDto;
import com.example.crudpractice.order.domain.Order;
import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.order.domain.repository.OrderProductRepository;
import com.example.crudpractice.order.domain.repository.OrderRepository;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final Check check;

    @Transactional
    public void createOrder(OrderCreateReqDto orderCreateReqDto) {
        Member member = check.checkMember(orderCreateReqDto.memberId());
        Product product = check.checkProductName(orderCreateReqDto.name());

        // 상품 수량 검증
        if (product.getQuantity() < orderCreateReqDto.count())
            throw new IllegalArgumentException("상품 수량이 부족합니다.");
        product.decreaseQuantity(orderCreateReqDto.count());

        // 주문 생성 및 주문 상품 생성
        Order order = orderCreateReqDto.toOrder(member);
        OrderProduct orderProduct = orderCreateReqDto.toOrderProduct(order, product);

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
        Order order = check.checkOrder(orderId);

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
