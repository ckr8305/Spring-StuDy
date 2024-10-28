package com.example.crudpractice.member.domain.repository;

import com.example.crudpractice.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
