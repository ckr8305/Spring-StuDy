package com.example.crudpractice.member.api.dto.response;

import com.example.crudpractice.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberInfoResDto(
        String name,
        String email
) {
    public static MemberInfoResDto from(Member member) {
        return MemberInfoResDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}
