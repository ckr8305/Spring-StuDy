package com.example.crudpractice.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Builder
    public MemberUpdateRequest(String name) {
        this.name = name;
    }
}
