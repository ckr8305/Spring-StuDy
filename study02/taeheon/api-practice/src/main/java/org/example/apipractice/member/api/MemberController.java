package org.example.apipractice.member.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.apipractice.global.template.RspTemplate;
import org.example.apipractice.member.api.dto.request.MemberEmailRequest;
import org.example.apipractice.member.api.dto.request.MemberLoginRequest;
import org.example.apipractice.member.api.dto.request.MemberSaveRequest;
import org.example.apipractice.member.api.dto.request.RefreshRequest;
import org.example.apipractice.member.api.dto.response.MemberLoginResponse;
import org.example.apipractice.member.application.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public RspTemplate<String> join(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        memberService.join(memberSaveRequest);
        return new RspTemplate<>(HttpStatus.CREATED, "회원가입");
    }

    @GetMapping()
    public RspTemplate<MemberLoginResponse> login(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {
        MemberLoginResponse memberLoginResponse = memberService.login(memberLoginRequest);
        return new RspTemplate<>(HttpStatus.OK, "로그인", memberLoginResponse);
    }

    // Access Token 갱신
    @PostMapping("/refresh")
    public RspTemplate<String> updateAccessToken(@RequestBody @Valid RefreshRequest refreshRequest) {
        String newAccessToken = memberService.updateAccessToken(refreshRequest);
        return new RspTemplate<>(HttpStatus.OK, "Access Token 갱신", newAccessToken);
    }

    @PostMapping("/logout")
    public RspTemplate<Void> logout(@RequestBody @Valid MemberEmailRequest email) {
        memberService.logout(email);
        return new RspTemplate<>(HttpStatus.OK, "로그아웃");
    }
}
