package com.aaa.jeppy.component;

import com.aaa.jeppy.member.entity.Member;
import com.aaa.jeppy.member.entity.Member.SocialProvider;
import com.aaa.jeppy.member.repository.MemberRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MemberDataInitializer {

    private final MemberRepository memberRepository;

    public MemberDataInitializer(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // @PostConstruct
    // public void init() {
    //     try {
    //     Member member1 = new Member();

    //     member1.setEmail("test@naver.com");
    //     member1.setProvider(SocialProvider.KAKAO);
    //     member1.setMembername("김덕배");
        
    //     memberRepository.save(member1);
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }

    //     try {
    //         Member member2 = new Member();

    //         member2.setEmail("test1@naver.com");
    //         member2.setProvider(SocialProvider.GOOGLE);
    //         member2.setMembername("손흥민");

    //         memberRepository.save(member2);
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }
}
