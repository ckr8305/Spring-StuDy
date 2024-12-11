package org.example.apipractice.member.api.dto.response;

import lombok.Builder;
import org.example.apipractice.member.domain.Member;

@Builder
public record MemberInfoResponse(

        String nickname,
        String email,
        int age
) {

    public static MemberInfoResponse toDto(Member member) {
        return MemberInfoResponse.builder()
                .nickname(member.getNickName())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }
}
