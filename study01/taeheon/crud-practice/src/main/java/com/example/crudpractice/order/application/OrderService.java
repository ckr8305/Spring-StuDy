package com.example.crudpractice.order.application;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.member.domain.repository.MemberRepository;
import com.example.crudpractice.order.api.dto.request.OrderSaveRequest;
import com.example.crudpractice.order.api.dto.response.OrderInfoResponse;
import com.example.crudpractice.order.api.dto.response.OrderProductResponse;
import com.example.crudpractice.order.api.util.OrderConverter;
import com.example.crudpractice.order.domain.Order;
import com.example.crudpractice.order.domain.OrderProduct;
import com.example.crudpractice.order.domain.repository.OrderProductRepository;
import com.example.crudpractice.order.domain.repository.OrderRepository;
import com.example.crudpractice.product.domain.Product;
import com.example.crudpractice.product.domain.repository.ProductRepository;
import com.example.crudpractice.product.api.dto.request.ProductUpdateRequest;
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
    public void orderSave(OrderSaveRequest orderSaveRequest) {
        Long memberId = orderSaveRequest.memberId();
        Member member = checkMember(memberId);

        // 주문 생성
        Order order = OrderConverter.toOrderEntity(orderSaveRequest, member);
        orderRepository.save(order);

        // 주문 상품 목록: 싱품 ID 목록과 상품의 수량
        for (int i = 0; i < orderSaveRequest.productIds().size(); i++) {
            Long productId = orderSaveRequest.productIds().get(i);
            int count = orderSaveRequest.counts().get(i);

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다. id = " + productId));

            // 주문 상품 저장
            OrderProduct orderProduct = OrderConverter.toOrderProductEntity(order, product, count);
            orderProductRepository.save(orderProduct);

            // 제품 수량 감소
            product.reduceProductQuantity(count);
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

    private Member checkMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        return member;
    }
}
