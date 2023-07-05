package com.aaa.jeppy.member.service;

import com.aaa.jeppy.member.dto.request.MemberRegistrationRequestDTO;
import com.aaa.jeppy.member.entity.Member;

public interface MemberAuthService {

    boolean isMemberRegistered(String memberEmail);
    Member getEmailFromMember(String Email);
    Member getMemberFromToken(String token);
    String authenticateMember(Member member);

    String handleKakaoCallback(String code);

    // Member registerKakaoMember(MemberRegistrationRequestDTO memberRegistrationRequestDTO);
}
