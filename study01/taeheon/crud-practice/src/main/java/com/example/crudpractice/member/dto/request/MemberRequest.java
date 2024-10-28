package com.example.crudpractice.member.dto.request;

import com.example.crudpractice.order.domain.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MemberRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @Email
    private String email;
    private LocalDateTime createAt;
    private List<Order> orders;


    @Builder
    public MemberRequest(String name, String email, LocalDateTime creatAt, List<Order> orders) {
        this.name = name;
        this.email = email;
        this.createAt = creatAt;
        this.orders = orders;
    }
}
