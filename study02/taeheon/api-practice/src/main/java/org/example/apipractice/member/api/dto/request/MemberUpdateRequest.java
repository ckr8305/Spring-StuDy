package org.example.apipractice.member.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberUpdateRequest(
        @NotBlank
        String nickname,
        @NotBlank
        String password,
        int age
) {
}
