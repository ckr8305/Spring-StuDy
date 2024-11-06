package com.example.crudpractice.order.domain.repository;

import com.example.crudpractice.order.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberMemberId(long memberId);
}
