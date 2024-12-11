package org.example.apipractice.member.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.apipractice.global.template.RspTemplate;
import org.example.apipractice.member.api.dto.request.*;
import org.example.apipractice.member.api.dto.response.MemberInfoResponse;
import org.example.apipractice.member.api.dto.response.MemberLoginResponse;
import org.example.apipractice.member.application.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public RspTemplate<String> register(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        memberService.register(memberSaveRequest);
        return new RspTemplate<>(HttpStatus.CREATED, "회원가입");
    }

    @GetMapping("/login")
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

    @GetMapping("/{id}")
    public RspTemplate<MemberInfoResponse> findMemberById(@PathVariable Long id) {
        MemberInfoResponse memberInfo = memberService.findMemberById(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 정보 조회", memberInfo);
    }

    @GetMapping()
    public RspTemplate<List<MemberInfoResponse>> findMembers() {
        List<MemberInfoResponse> memberInfoList = memberService.findAllMember();
        return new RspTemplate<>(HttpStatus.OK, "전체 사용자 정보 조회", memberInfoList);
    }

    @PatchMapping("/{id}")
    public RspTemplate<MemberInfoResponse> updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(id, memberUpdateRequest);
        MemberInfoResponse memberInfoResponse = memberService.findMemberById(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 정보 변경", memberInfoResponse);
    }

    @DeleteMapping("/{id}")
    public RspTemplate<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 삭제");
    }
}
