package com.example.crudpractice.order.domain;

import com.example.crudpractice.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member = new Member();

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderProduct> orderProductList = new ArrayList<>();

    @Builder
    public Order(LocalDateTime createAt, Member member) {
        this.createAt = createAt;
        this.member = member;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProductList.add(orderProduct);
    }
}
