package com.example.crudpractice.member.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberSaveRequest(
        @NotBlank
        @Size(min = 3, max = 50)
        String name,
        @Email
        String email
) {
}
