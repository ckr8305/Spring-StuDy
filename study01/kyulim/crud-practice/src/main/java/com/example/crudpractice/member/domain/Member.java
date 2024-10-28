package com.example.crudpractice.member.domain;

import com.example.crudpractice.member.api.dto.reqeust.MemberReqDto;
import com.example.crudpractice.order.domain.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long memberId;

    private String name;
    private String email;

    private LocalDateTime createAt;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, String email, LocalDateTime createAt) {
        this.name = name;
        this.email = email;
        this.createAt = createAt;
    }

    public void update(MemberReqDto memberReqDto) {
        this.name = name;
        this.email = email;
    }
}
