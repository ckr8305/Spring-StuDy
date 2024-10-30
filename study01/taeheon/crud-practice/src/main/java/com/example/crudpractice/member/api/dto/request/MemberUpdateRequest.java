package com.example.crudpractice.member.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record MemberUpdateRequest(
        @NotBlank
        @Size(min = 3, max = 50)
        String name
) {
    @Builder
    public MemberUpdateRequest(String name) {
        this.name = name;
    }
}
