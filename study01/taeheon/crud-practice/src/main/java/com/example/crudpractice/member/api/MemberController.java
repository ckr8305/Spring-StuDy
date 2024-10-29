package com.example.crudpractice.member.api;

import com.example.crudpractice.global.template.RspTemplate;
import com.example.crudpractice.member.application.MemberService;
import com.example.crudpractice.member.dto.request.MemberSaveRequest;
import com.example.crudpractice.member.dto.request.MemberUpdateRequest;
import com.example.crudpractice.member.dto.response.MemberInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
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

    @GetMapping("/{id}")
    public RspTemplate<MemberInfoResponse> findMemberById(@PathVariable("id") Long id) {
        MemberInfoResponse member = memberService.findMemberById(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 조회", member);
    }

    @PatchMapping("/{id}")
    public RspTemplate<MemberInfoResponse> updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequest requestDto) {
        memberService.updateMember(id, requestDto);
        MemberInfoResponse member = memberService.findMemberById(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 수정", member);
    }

    @DeleteMapping("/{id}")
    public RspTemplate<String> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 삭제");
    }
}
