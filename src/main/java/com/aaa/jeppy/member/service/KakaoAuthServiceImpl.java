package com.aaa.jeppy.member.service;

import com.aaa.jeppy.member.entity.Member;
import com.aaa.jeppy.common.util.HttpClientUtil;
import com.aaa.jeppy.common.util.JsonUtil;
import com.aaa.jeppy.member.dto.response.KakaoUserResponseDTO;
import com.aaa.jeppy.member.dto.response.KakaoTokenResponseDTO;
import com.aaa.jeppy.member.entity.Member;

import java.util.HashMap;
import java.util.Map;

public class KakaoAuthServiceImpl implements KakaoAuthService{

    private static final String KAKAO_API_TOKEN_URL = "https://kakaoapis.com/oauth/token";
    private static final String KAKAO_API_USER_URL = "https://kakaoapis.com/v2/user/me";
    private static final String CLIENT_ID = "CLIENT_ID";
    private static final String REDIRECT_URI = "REDIRECT_URI";

    private HttpClientUtil httpClientUtil;
    private JsonUtil jsonUtil;

    public KakaoAuthServiceImpl() {
        this.httpClientUtil = new HttpClientUtil();
        this.jsonUtil = new JsonUtil();
    }
    @Override
    public String getAccessToken(String code) {
        // 카카오 API로 액세스 토큰을 요청하기 위한 파라미터 설정
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "authorization_code");
        parameters.put("client_id", CLIENT_ID);
        parameters.put("redirect_uri", REDIRECT_URI);
        parameters.put("code", code);

        // HTTP POST 요청을 통해 액세스 토큰 요청
        String response = httpClientUtil.sendPostRequest(KAKAO_API_TOKEN_URL, parameters);

        // 응답에서 액세스 토큰 추출
        KakaoTokenResponseDTO tokenResponse = jsonUtil.fromJson(response, KakaoTokenResponseDTO.class);
        String accessToken = tokenResponse.getAccessToken();

        return accessToken;
    }

    @Override
    public Member getMemberInfo(String accessToken) {
        // 카카오 API로 사용자 정보 요청하기 위한 헤더 설정
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);

        // HTTP GET 요청을 통해 사용자 정보 요청
        String response = httpClientUtil.sendGetRequest(KAKAO_API_USER_URL, headers);

        // 응답에서 사용자 정보 추출
        KakaoUserResponseDTO userResponse = jsonUtil.fromJson(response, KakaoUserResponseDTO.class);
        Member member = new Member();
        member.setEmail(userResponse.getEmail());
        member.setMemberName(userResponse.getMemberName());
        // ...

        return member;
    }
}
