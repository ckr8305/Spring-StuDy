package com.example.crudpractice.member.api.dto.request;

import com.example.crudpractice.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record MemberSaveRequest(
        @NotBlank
        @Size(min = 3, max = 50)
        String name,
        @Email
        String email
) {

    public Member toEntity(MemberSaveRequest requestDto) {
        return Member.builder()
                .name(requestDto.name)
                .email(requestDto.email)
                .createAt(LocalDateTime.now())
                .build();
    }
}
