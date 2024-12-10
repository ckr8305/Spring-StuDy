package org.example.apipractice.global.jwt.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // Refresh Token 소유자

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiration;

    public RefreshToken(String email, String token, LocalDateTime expiration) {
        this.email = email;
        this.token = token;
        this.expiration = expiration;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiration);
    }
}
