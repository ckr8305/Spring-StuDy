package com.example.crudpractice.orderProduct.domain.repository;

import com.example.crudpractice.orderProduct.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
