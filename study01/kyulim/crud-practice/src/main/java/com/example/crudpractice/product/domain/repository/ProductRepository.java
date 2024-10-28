package com.example.crudpractice.product.domain.repository;

import com.example.crudpractice.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByName(String name);
}
