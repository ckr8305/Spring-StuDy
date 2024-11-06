package com.example.crudpractice.order.api;

import com.example.crudpractice.global.RspTemplate;
import com.example.crudpractice.order.api.dto.request.OrderCreateReqDto;
import com.example.crudpractice.order.api.dto.response.OrderResDto;
import com.example.crudpractice.order.application.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    public final OrderService orderService;

    @PostMapping("/{id}")
    public RspTemplate<Void> createOrder(@PathVariable("id") long memberId,
                                        @RequestBody OrderCreateReqDto orderCreateReqDto) {
        orderService.createOrder(memberId, orderCreateReqDto.name(), orderCreateReqDto.count());
        return new RspTemplate<>(HttpStatus.CREATED, "주문 등록 !");
    }

    @GetMapping("/{id}")
    public RspTemplate<List<OrderResDto>> getOrderDetail(@PathVariable("id") long memberId) {
        List<OrderResDto> orderDetail = orderService.getOrderDetail(memberId);
        return new RspTemplate<>(HttpStatus.OK, "주문 조회 !", orderDetail);
    }

    @DeleteMapping("/{id}")
    public RspTemplate<Void> deleteOrder(@PathVariable("id") long orderId) {
        orderService.deleteOrder(orderId);
        return new RspTemplate<>(HttpStatus.OK, "주문 삭제 !");
    }
}
