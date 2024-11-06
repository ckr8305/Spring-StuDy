package com.example.crudpractice.order.api;

import com.example.crudpractice.global.RspTemplate;
import com.example.crudpractice.order.api.dto.request.OrderCreateReqDto;
import com.example.crudpractice.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/{id}/{name}")
    public RspTemplate<Void> order(@PathVariable("id") long memberId,
                                    @PathVariable("name") String name,
                                    @RequestBody OrderCreateReqDto orderCreateReqDto) {
        orderService.order(memberId, name, orderCreateReqDto.count());
        return new RspTemplate<>(HttpStatus.CREATED, "주문 등록 !");
    }
}
