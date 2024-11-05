package com.example.crudpractice.member.api.util;

import com.example.crudpractice.member.api.dto.request.MemberSaveRequest;
import com.example.crudpractice.member.api.dto.response.MemberInfoResponse;
import com.example.crudpractice.member.domain.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberConverter {

    public static MemberInfoResponse toDTO(Member member) {
        return new MemberInfoResponse(member.getName(), member.getEmail());
    }

    public static Member toEntity(MemberSaveRequest requestDto) {
        return Member.builder()
                .name(requestDto.name())
                .email(requestDto.email())
                .createAt(LocalDateTime.now())
                .build();
    }
}
