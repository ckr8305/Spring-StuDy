package com.example.crudpractice.member.api.dto.reqeust;

import com.example.crudpractice.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberCreateReqDto(
        String name,
        String email
) {
    public Member toEntity(MemberCreateReqDto memberReqDto) {
        return Member.builder()
                .name(memberReqDto.name())
                .email(memberReqDto.email())
                .build();
    }
}
