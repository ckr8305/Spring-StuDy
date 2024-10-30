package com.example.crudpractice.member.api.dto.response;

import com.example.crudpractice.member.domain.Member;

public record MemberInfoResponse(
        String name,
        String email
) {
    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(member.getName(), member.getEmail());
    }
}
