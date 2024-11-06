package com.example.crudpractice.order.domain.repository;

import com.example.crudpractice.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
