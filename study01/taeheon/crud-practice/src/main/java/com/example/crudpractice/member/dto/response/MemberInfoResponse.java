package com.example.crudpractice.member.dto.response;

import com.example.crudpractice.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberInfoResponse {

    private String name;
    private String email;

    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(member.getName(), member.getEmail());
    }
}
