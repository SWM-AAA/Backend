package com.aaa.jeppy.member.dto.request;

import lombok.Data;
import com.aaa.jeppy.member.entity.Member;

@Data
public class MemberRegistrationRequestDTO {
    private String email;
    private String membername;
    private Member.SocialProvider provider;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .membername(membername)
                .provider(provider)
                .build();
    }
}
