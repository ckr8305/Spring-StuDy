package org.example.apipractice.member.api.dto.response;

import lombok.Builder;
import org.example.apipractice.member.domain.Member;

@Builder
public record MemberLoginResponse(
        String email,
        String nickname,
        String accessToken,
        String refreshToken
) {
    public static MemberLoginResponse of(Member member, String accessToken, String refreshToken) {
        return MemberLoginResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickName())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
