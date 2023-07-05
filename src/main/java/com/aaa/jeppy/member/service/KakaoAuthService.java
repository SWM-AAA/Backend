package com.aaa.jeppy.member.service;

import com.aaa.jeppy.member.entity.Member;

public interface KakaoAuthService {
    String getAccessToken(String code);
    Member getMemberInfo(String accessToken);
    
}
