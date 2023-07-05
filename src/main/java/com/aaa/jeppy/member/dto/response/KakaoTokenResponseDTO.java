package com.aaa.jeppy.member.dto.response;

import lombok.Data;

@Data
public class KakaoTokenResponseDTO {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
}
