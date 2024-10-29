package com.example.crudpractice.member.dto.request;

import com.example.crudpractice.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberSaveRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @Email
    private String email;

    public Member toEntity(MemberSaveRequest requestDto) {
        return Member.builder()
                .name(requestDto.name)
                .email(requestDto.email)
                .createAt(LocalDateTime.now())
                .build();
    }
}
