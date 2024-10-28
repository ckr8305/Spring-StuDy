package com.example.crudpractice.member.dto.response;

import com.example.crudpractice.order.domain.Order;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponse {

    private String name;
    private String email;
    private LocalDateTime createAt;
    private List<Order> orders;
}
