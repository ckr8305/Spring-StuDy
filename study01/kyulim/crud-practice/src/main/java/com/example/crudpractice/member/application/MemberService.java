package com.example.crudpractice.member.application;

import com.example.crudpractice.global.util.Check;
import com.example.crudpractice.member.api.dto.reqeust.MemberCreateReqDto;
import com.example.crudpractice.member.api.dto.reqeust.MemberUpdateReqDto;
import com.example.crudpractice.member.api.dto.response.MemberInfoResDto;
import com.example.crudpractice.member.api.dto.response.MemberListResDto;
import com.example.crudpractice.member.domain.Member;
import com.example.crudpractice.member.domain.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final Check check;

    @Transactional
    public void saveMember(MemberCreateReqDto requestDto) {
        memberRepository.save(requestDto.toEntity(requestDto));
    }

    public MemberListResDto getAllMember() {
        List<Member> members = memberRepository.findAll();

        List<MemberInfoResDto> memberInfoResDtos = members.stream()
                .map(MemberInfoResDto::from)
                .toList();

        return MemberListResDto.from(memberInfoResDtos);
    }

    public MemberInfoResDto getMemberById(long id) {
        return MemberInfoResDto.from(check.checkMember(id));
    }

    @Transactional
    public void updateMember(long id, MemberUpdateReqDto memberUpdateReqDto) {
        check.checkMember(id).update(memberUpdateReqDto);
    }

    @Transactional
    public void deleteMember(long id) {
        memberRepository.delete(check.checkMember(id));
    }
}
