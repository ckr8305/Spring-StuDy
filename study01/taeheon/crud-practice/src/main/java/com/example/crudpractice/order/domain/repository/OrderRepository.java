package com.example.crudpractice.order.domain.repository;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderIdAndMember(Long orderId, Member member);
}
