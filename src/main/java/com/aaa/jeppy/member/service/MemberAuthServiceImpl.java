package com.aaa.jeppy.member.service;

import com.aaa.jeppy.common.util.JwtTokenUtil;
import com.aaa.jeppy.member.entity.Member;
import com.aaa.jeppy.member.repository.MemberRepository;
import com.aaa.jeppy.member.dto.request.MemberRegistrationRequestDTO;
import com.aaa.jeppy.member.dto.response.KakaoTokenResponseDTO;
import com.aaa.jeppy.member.dto.response.MemberResponseDTO;
import com.aaa.jeppy.member.service.KakaoAuthServiceImpl;

public class MemberAuthServiceImpl implements MemberAuthService {

    private MemberRepository memberRepository;
    private JwtTokenUtil jwtTokenUtil;
    private KakaoAuthService kakaoAuthService;

    public MemberAuthServiceImpl(MemberRepository memberRepository, JwtTokenUtil jwtTokenUtil, KakaoAuthService kakaoAuthService) {
        this.memberRepository = memberRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.kakaoAuthService = kakaoAuthService;
    }

    @Override
    public boolean isMemberRegistered(String memberEmail) {
        Member member = memberRepository.findByEmail(memberEmail);
        return member != null;
    }

    @Override
    public Member getEmailFromMember(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Member getMemberFromToken(String token) {
        String email = jwtTokenUtil.getEmailFromToken(token);
        return getEmailFromMember(email);
    }

    @Override
    public String authenticateMember(Member member) {
        Member existingMember = getEmailFromMember(member.getEmail());
        if (existingMember != null) {
            return jwtTokenUtil.generateToken(existingMember);
        } else {
            memberRepository.save(member);
            return jwtTokenUtil.generateToken(member);
        }
    }

    @Override
    public String handleKakaoCallback(String code) {
        // 카카오 API로부터 액세스 토큰 발급 요청
        String accessToken = kakaoAuthService.getAccessToken(code);

        // 액세스 토큰을 사용하여 사용자 정보 요청
        Member memberinfo = kakaoAuthService.getMemberInfo(accessToken);

        // 카카오 사용자 정보를 기반으로 MemberDTO 생성
        MemberRegistrationRequestDTO memberRegistrationRequestDTO = new MemberRegistrationRequestDTO();
        memberRegistrationRequestDTO.setEmail(memberinfo.getEmail());
        memberRegistrationRequestDTO.setMembername(memberinfo.getMembername());
        // ...

        // MemberDTO를 Member 엔티티로 변환
        Member member = memberRegistrationRequestDTO.toEntity();

        // 카카오 사용자가 이미 가입되어 있는지 확인
        boolean isRegistered = isMemberRegistered(member.getEmail());

        if (isRegistered) {
            // 가입된 사용자인 경우, 액세스 토큰을 발급하고 반환
            String zeppyAccessToken = jwtTokenUtil.generateToken(member);
            return zeppyAccessToken;
        } else {
            // 가입되지 않은 사용자인 경우, 회원 가입 처리 후 액세스 토큰을 발급하고 반환
            memberRepository.save(member);
            String zeppyAccessToken = jwtTokenUtil.generateToken(member);
            return zeppyAccessToken;
        }
    }
}
