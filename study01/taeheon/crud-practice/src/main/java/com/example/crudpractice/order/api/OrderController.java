package com.example.crudpractice.order.api;

import com.example.crudpractice.global.template.RspTemplate;
import com.example.crudpractice.order.api.dto.request.OrderSaveRequest;
import com.example.crudpractice.order.api.dto.response.OrderInfoResponse;
import com.example.crudpractice.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{memberId}")
    public RspTemplate<Void> orderSave(@PathVariable("memberId") Long memberId, @RequestBody OrderSaveRequest orderSaveRequest) {
        orderService.orderSave(orderSaveRequest, memberId);
        return new RspTemplate<>(HttpStatus.CREATED, "주문 저장");
    }

    @GetMapping("/{memberId}")
    public RspTemplate<List<OrderInfoResponse>> getOrderByMember(@PathVariable("memberId") Long memberId) {
        List<OrderInfoResponse> orderInfoResponseList = orderService.findOrderInfoByMemberId(memberId);
        return new RspTemplate<>(HttpStatus.OK, "사용자 주문 목록", orderInfoResponseList);
    }

    @DeleteMapping("/{memberId}/{orderId}")
    public RspTemplate<Void> deleteOrder(@PathVariable("memberId") Long memberId, @PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(memberId, orderId);
        return new RspTemplate<>(HttpStatus.OK, "주문 삭제");
    }
}
