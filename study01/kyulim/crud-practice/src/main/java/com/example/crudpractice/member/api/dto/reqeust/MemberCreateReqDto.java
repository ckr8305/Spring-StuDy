package com.example.crudpractice.member.api.dto.reqeust;

import com.example.crudpractice.member.domain.Member;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MemberCreateReqDto(
        String name,
        String email,
        LocalDateTime createAt
) {
    public Member toEntity(MemberCreateReqDto memberReqDto) {
        return Member.builder()
                .name(memberReqDto.name())
                .email(memberReqDto.email())
                .createAt(LocalDateTime.now())
                .build();
    }
}
