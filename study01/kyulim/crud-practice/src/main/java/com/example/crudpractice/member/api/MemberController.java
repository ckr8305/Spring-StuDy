package com.example.crudpractice.member.api;

import com.example.crudpractice.member.api.dto.reqeust.MemberReqDto;
import com.example.crudpractice.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<String> saveMember(@RequestBody MemberReqDto memberReqDto) {
        memberService.saveMember(memberReqDto);
        return new ResponseEntity<>("사용자 저장 !", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<String> findMembers() {
        memberService.getAllMember();
        return new ResponseEntity<>("전체 사용자 조회 !", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findMemberById(@PathVariable("id") long id) {
        memberService.getMemberById(id);
        return new ResponseEntity<>("사용자 조회 !", HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<String> updateMember(@PathVariable("id") long id, MemberReqDto memberReqDto) {
        memberService.updateMember(id, memberReqDto);
        return new ResponseEntity<>("사용자 수정 !", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteMember(@PathVariable("id") long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>("사용자 삭제 !", HttpStatus.OK);
    }
}
