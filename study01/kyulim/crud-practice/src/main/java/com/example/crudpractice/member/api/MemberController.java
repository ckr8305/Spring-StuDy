package com.example.crudpractice.member.api;

import com.example.crudpractice.global.RspTemplate;
import com.example.crudpractice.member.api.dto.reqeust.MemberCreateReqDto;
import com.example.crudpractice.member.api.dto.reqeust.MemberUpdateReqDto;
import com.example.crudpractice.member.api.dto.response.MemberInfoResDto;
import com.example.crudpractice.member.api.dto.response.MemberListResDto;
import com.example.crudpractice.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public RspTemplate<Void> saveMember(@RequestBody MemberCreateReqDto memberReqDto) {
        memberService.saveMember(memberReqDto);
        return new RspTemplate<>(HttpStatus.CREATED, "사용자 등록 !");
    }

    @GetMapping()
    public RspTemplate<MemberListResDto> findMembers() {
        MemberListResDto members = memberService.getAllMember();
        return new RspTemplate<>(HttpStatus.OK, "전체 사용자 조회 !", members);
    }

    @GetMapping("/{id}")
    public RspTemplate<MemberInfoResDto> findMemberById(@PathVariable("id") long id) {
        MemberInfoResDto member = memberService.getMemberById(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 조회 !", member);
    }

    @PatchMapping("/{id}")
    public RspTemplate<MemberInfoResDto> updateMember(@PathVariable("id") long id, @RequestBody MemberUpdateReqDto memberUpdateReqDto) {
        memberService.updateMember(id, memberUpdateReqDto);
        MemberInfoResDto member = memberService.getMemberById(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 조회 !", member);
    }

    @DeleteMapping()
    public RspTemplate<String> deleteMember(@PathVariable("id") long id) {
        memberService.deleteMember(id);
        return new RspTemplate<>(HttpStatus.OK, "사용자 삭제 !");
    }
}
