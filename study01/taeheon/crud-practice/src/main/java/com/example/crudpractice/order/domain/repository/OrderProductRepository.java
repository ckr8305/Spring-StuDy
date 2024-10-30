package com.example.crudpractice.order.domain.repository;

import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query(value = "select op " +
            "from OrderProduct  op join fetch op.order o " +
            "where op.order.member = :member") // :member는 메서드 파라미터로 전달된 Member 객체를 바인딩 (위치 파라미터)
    List<OrderProduct> findAllByMember(Member member);
}
