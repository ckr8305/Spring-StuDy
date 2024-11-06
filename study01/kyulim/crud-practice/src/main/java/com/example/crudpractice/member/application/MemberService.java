package com.example.crudpractice.member.application;

import com.example.crudpractice.member.api.dto.reqeust.MemberCreateReqDto;
import com.example.crudpractice.member.api.dto.reqeust.MemberUpdateReqDto;
import com.example.crudpractice.member.api.dto.response.MemberInfoResDto;
import com.example.crudpractice.member.api.dto.response.MemberListResDto;
import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(MemberCreateReqDto requestDto) {
        Member member = requestDto.toEntity(requestDto);
        memberRepository.save(member);
    }

    public MemberListResDto getAllMember() {
        List<Member> members = memberRepository.findAll();

        List<MemberInfoResDto> memberInfoResDtos = members.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDtos);
    }

    public MemberInfoResDto getMemberById(long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return MemberInfoResDto.from(member);
    }

    @Transactional
    public void updateMember(long id, MemberUpdateReqDto memberUpdateReqDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        member.update(memberUpdateReqDto);
    }

    @Transactional
    public void deleteMember(long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        memberRepository.delete(member);
    }
}
