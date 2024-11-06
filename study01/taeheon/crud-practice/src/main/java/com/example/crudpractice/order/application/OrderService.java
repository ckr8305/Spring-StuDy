package com.example.crudpractice.order.application;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.member.domain.repository.MemberRepository;
import com.example.crudpractice.order.api.dto.request.OrderSaveRequest;
import com.example.crudpractice.order.api.dto.response.OrderInfoResponse;
import com.example.crudpractice.order.api.util.OrderConverter;
import com.example.crudpractice.order.domain.Order;
import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.order.domain.repository.OrderProductRepository;
import com.example.crudpractice.order.domain.repository.OrderRepository;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void orderSave(OrderSaveRequest orderSaveRequest, Long memberId) {
        Member member = checkMember(memberId);

        // 주문 생성
        Order order = OrderConverter.toOrderEntity(member);
        orderRepository.save(order);

        // 주문 상품 목록: 싱품 ID 목록과 상품의 수량
        for (int i = 0; i < orderSaveRequest.productIds().size(); i++) {
            Long productId = orderSaveRequest.productIds().get(i);
            int count = orderSaveRequest.counts().get(i);

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id = " + productId));

            if (count > product.getQuantity()) {
                throw new IllegalArgumentException("상품 수량이 부족합니다.");
            }
            // 제품 수량 감소
            product.reduceProductQuantity(count);

            // 주문 상품 저장
            OrderProduct orderProduct = OrderConverter.toOrderProductEntity(order, product, count);
            orderProductRepository.save(orderProduct);
        }
    }

    public List<OrderInfoResponse> findOrderInfoByMemberId(Long id) {
        Member member = checkMember(id);
        List<OrderProduct> allByOrderProduct = orderProductRepository.findAllByMember(member);

        List<OrderInfoResponse> orderInfoResponseList = new ArrayList<>();
        for (OrderProduct orderProduct : allByOrderProduct) {
            OrderInfoResponse response = OrderConverter.toOrderDTO(orderProduct);
            orderInfoResponseList.add(response);
        }
        return orderInfoResponseList;
    }

    @Transactional
    public void deleteOrder(Long memberId, Long orderId) {
        Member member = checkMember(memberId);
        Order order = orderRepository.findByOrderIdAndMember(orderId, member)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 주문이 없습니다 id = " + orderId + ", member Id = " + memberId));

        List<OrderProduct> orderProducts = orderProductRepository.findByOrder(order);

        for (OrderProduct orderProduct : orderProducts) {
            Product product = orderProduct.getProduct();
            int count = orderProduct.getTotalCount();

            // 제품 수량 증가
            product.increaseProductQuantity(count);
            productRepository.save(product);
        }

        // 주문 항목 삭제
        orderProductRepository.deleteAll(orderProducts);
        orderRepository.delete(order);
    }

    private Member checkMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        return member;
    }
}
