package org.example.apipractice.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apipractice.global.jwt.TokenProvider;
import org.example.apipractice.global.jwt.domain.RefreshToken;
import org.example.apipractice.global.jwt.domain.RefreshTokenRepository;
import org.example.apipractice.member.api.dto.request.MemberEmailRequest;
import org.example.apipractice.member.api.dto.request.MemberLoginRequest;
import org.example.apipractice.member.api.dto.request.MemberSaveRequest;
import org.example.apipractice.member.api.dto.request.RefreshRequest;
import org.example.apipractice.member.api.dto.response.MemberLoginResponse;
import org.example.apipractice.member.domain.Member;
import org.example.apipractice.member.domain.MemberRepository;
import org.example.apipractice.member.exception.InvalidMemberException;
import org.example.apipractice.member.exception.NotFoundMemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원 가입
    @Transactional
    public void join(MemberSaveRequest memberSaveRequest) {
        // 이메일 존재 여부
        if (memberRepository.existsByEmail(memberSaveRequest.email())) {
            throw new InvalidMemberException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .email(memberSaveRequest.email())
                .password(memberSaveRequest.password())
                .nickName(memberSaveRequest.nickname())
                .age(memberSaveRequest.age())
                .createAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);
    }

    // 로그인 성공 시 토큰 발급
    @Transactional
    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByEmail(memberLoginRequest.email())
                .orElseThrow(NotFoundMemberException::new);
        if (!member.getPassword().equals(memberLoginRequest.password())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = tokenProvider.generateAccessToken(member.getEmail());
        String refreshToken = tokenProvider.generateRefreshToken(member.getEmail());
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(tokenProvider.getRefreshTokenExpirationSeconds());

        // RefreshToken 저장
        refreshTokenRepository.deleteByEmail(member.getEmail()); // 기존 토큰 제거
        refreshTokenRepository.save(new RefreshToken(member.getEmail(), refreshToken, expiration));

        return MemberLoginResponse.of(member, accessToken, refreshToken);
    }

    // AccessToken 갱신
    public String updateAccessToken(RefreshRequest refreshRequest) {
        String email = tokenProvider.getEmailFromToken(refreshRequest.refreshToken());
        RefreshToken storedToken = refreshTokenRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("Refresh Token이 존재하지 않습니다."));

        if (storedToken.isExpired() || !storedToken.getToken().equals(refreshRequest.refreshToken())) {
            new IllegalArgumentException("Token이 만료되거나 일치하지 않습니다.");
        }

        // 새로운 AccessToken 발급
        return tokenProvider.generateAccessToken(email);
    }

    @Transactional
    public void logout(MemberEmailRequest memberEmailRequest) {
        System.out.println(memberEmailRequest.email());
        log.debug("email -> {}", memberEmailRequest.email());
        // 이메일이 존재하는지 확인
        if (!refreshTokenRepository.existsByEmail(memberEmailRequest.email())) {
            throw new IllegalArgumentException("해당하는 email이 존재하지 않습니다.");
        }
        // 이메일 존재 시 삭제
        refreshTokenRepository.deleteByEmail(memberEmailRequest.email());
    }

}
