package org.example.apipractice.global.jwt;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

    private final JwtUtil jwtUtil;

    public TokenProvider(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String generateAccessToken(String email) {
        // 사용자 정보를 기반으로 토큰 생성
        return jwtUtil.createAccessToken(email);
    }

    public String generateRefreshToken(String email) {
        // 사용자 정보를 기반으로 토큰 생성
        return jwtUtil.createRefreshToken(email);
    }

    // 토큰에서 사용자 email 추출
    public String getEmailFromToken(String token) {
        Claims claims = jwtUtil.parseToken(token);
        return claims.getSubject(); // JWT의 Subject는 보통 사용자 식별자
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        return jwtUtil.isTokenValid(token);
    }

    public long getRefreshTokenExpirationSeconds() {
        return jwtUtil.getRefreshTokenExpirationSeconds();
    }
}
