package org.example.apipractice.member.api.dto.request;

import jakarta.validation.constraints.Email;

public record MemberEmailRequest(
        @Email
        String email
) {
}
