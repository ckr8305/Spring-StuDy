package com.example.crudpractice.member.application;

import com.example.crudpractice.member.api.dto.request.MemberSaveRequest;
import com.example.crudpractice.member.api.dto.request.MemberUpdateRequest;
import com.example.crudpractice.member.api.dto.response.MemberInfoResponse;
import com.example.crudpractice.member.api.util.MemberConverter;
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

    @Transactional // 변경
    public void saveMember(MemberSaveRequest requestDto) {
        Member member = MemberConverter.toEntity(requestDto);
        memberRepository.save(member);
    }

    public List<MemberInfoResponse> findAllMember() {
        return memberRepository.findAll().stream()
                .map(MemberConverter::toDTO)
                .toList();
    }

    public MemberInfoResponse findMemberById(Long memberId) {
        Member member = checkMember(memberId);
        return MemberConverter.toDTO(member);
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest requestDto) {
        Member member = checkMember(memberId);
        member.update(requestDto); // JPA로 인해 변경 사항이 DB에 자동 반영
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = checkMember(memberId);
        memberRepository.delete(member);
    }

    private Member checkMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("해당하는 사용자가 없습니다. id = " + memberId));
    }

}
