package org.example.apipractice.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.example.apipractice.member.exception.CustomAuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final SecretKey key;
    @Value("${jwt.access.token.expire}")
    private long accessTokenExpiration;
    @Value("${jwt.refresh.token.expire}")
    private long refreshTokenExpiration;

    public JwtUtil(@Value("${jwt.secret.key}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // AccessToken 생성
    public String createAccessToken(String email) {
        return Jwts.builder()
                .subject(email) // 사용자 식별 정보
                .issuedAt(new Date()) // 발급 시간
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration)) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact();
    }

    // RefreshToken 생성
    public String createRefreshToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 파싱 및 검증
    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key) // 서명 키 설정
                .build()
                .parseSignedClaims(token) // 서명 및 Claims 파싱
                .getPayload();
    }

    // 토큰 유효성 검사
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (UnsupportedJwtException | MalformedJwtException exception) {
            log.error("JWT가 유효하지 않습니다.");
            throw new CustomAuthenticationException("JWT가 유효하지 않습니다.");
        } catch (ExpiredJwtException exception) {
            log.error("JWT가 만료되었습니다.");
            throw new CustomAuthenticationException("JWT가 만료되었습니다.");
        } catch (IllegalArgumentException exception) {
            log.error("JWT가 null이거나 비어 있거나 공백만 있습니다.");
            throw new CustomAuthenticationException("JWT가 null이거나 비어 있거나 공백만 있습니다.");
        } catch (Exception exception) {
            log.error("JWT 검증에 실패했습니다. ", exception.getMessage());
            throw new CustomAuthenticationException("JWT 검증에 실패했습니다.");
        }
    }

    public long getRefreshTokenExpirationSeconds() {
        return refreshTokenExpiration / 1000; // 초 단위로 반환
    }
}
