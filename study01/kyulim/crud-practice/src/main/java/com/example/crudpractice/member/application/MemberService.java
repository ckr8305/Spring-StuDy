package com.example.crudpractice.member.application;

import com.example.crudpractice.member.api.dto.reqeust.MemberReqDto;
import com.example.crudpractice.member.api.dto.response.MemberInfoResDto;
import com.example.crudpractice.member.api.dto.response.MemberListResDto;
import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(MemberReqDto requestDto) {
        Member member = requestDto.toEntity(requestDto);
        memberRepository.save(member);
    }

    public MemberListResDto findMembers() {
        List<Member> members = memberRepository.findAll();

        List<MemberInfoResDto> memberInfoResDtos = members.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDtos);
    }

    @Transactional
    public MemberInfoResDto findMemberById(long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return MemberInfoResDto.from(member);
    }

    @Transactional
    public void updateMember(long id, MemberReqDto memberReqDto) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.update(memberReqDto);
    }

    @Transactional
    public void deleteMember(long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
    }
}
