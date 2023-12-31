package com.aaa.jeppy.member.service;

import com.aaa.jeppy.member.model.Member;
import com.aaa.jeppy.member.repository.MemberRepository;

import org.springframework.stereotype.Service;

@Service
public class CheckMemberRegistedService implements IMemberService {

    private final MemberRepository memberRepository;

    public CheckMemberRegistedService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean isMemberRegistered(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail);
        return member != null;
    }
}

