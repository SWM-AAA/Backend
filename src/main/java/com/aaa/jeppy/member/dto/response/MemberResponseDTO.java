package com.aaa.jeppy.member.dto.response;

import com.aaa.jeppy.member.entity.Member;

import lombok.Data;

@Data
public class MemberResponseDTO {
    private Long id;
    private String email;
    private String membername;
    private Member.SocialProvider provider;
}
