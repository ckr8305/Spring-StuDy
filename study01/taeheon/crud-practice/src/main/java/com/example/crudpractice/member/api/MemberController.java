package com.example.crudpractice.member.api;

import com.example.crudpractice.global.template.RspTemplate;
import com.example.crudpractice.member.application.MemberService;
import com.example.crudpractice.member.api.dto.request.MemberSaveRequest;
import com.example.crudpractice.member.api.dto.request.MemberUpdateRequest;
import com.example.crudpractice.member.api.dto.response.MemberInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public RspTemplate<Void> saveMember(@RequestBody @Valid MemberSaveRequest requestDto) {
        memberService.saveMember(requestDto);
        return new RspTemplate<>(HttpStatus.CREATED, "사용자 저장");
    }

    @GetMapping()
    public RspTemplate<List<MemberInfoResponse>> findMembers() {
        List<MemberInfoResponse> members = memberService.findAllMember();
        return new RspTemplate<>(HttpStatus.OK, "전체 사용자 조회", members);
    }

    @GetMapping("/{memberId}")
    public RspTemplate<MemberInfoResponse> findMemberById(@PathVariable("memberId") Long memberId) {
        MemberInfoResponse member = memberService.findMemberById(memberId);
        return new RspTemplate<>(HttpStatus.OK, "사용자 조회", member);
    }

    @PatchMapping("/{memberId}")
    public RspTemplate<MemberInfoResponse> updateMember(@PathVariable("memberId") Long memberId, @RequestBody @Valid MemberUpdateRequest requestDto) {
        memberService.updateMember(memberId, requestDto);
        MemberInfoResponse member = memberService.findMemberById(memberId);
        return new RspTemplate<>(HttpStatus.OK, "사용자 수정", member);
    }

    @DeleteMapping("/{memberId}")
    public RspTemplate<String> deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return new RspTemplate<>(HttpStatus.OK, "사용자 삭제");
    }
}
