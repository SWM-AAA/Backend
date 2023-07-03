package com.aaa.jeppy.member.service;

import java.io.*;
import java.net.*;
import java.util.*;
import lombok.extern.slf4j.Slf4j;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KakaoService {
    public void execKakaoLogin(String authorize_code) {
        System.out.println("authorize_code: " + authorize_code);
        log.info("@@@@@@@authorize_code: " + authorize_code);

        String access_Token = getAccessToken(authorize_code);

        System.out.println("access_Token: " + access_Token);

        HashMap<String, Object> memberInfo = getMemberInfo(access_Token);

        System.out.println("login Controller : " + memberInfo);
    }

    public String getAccessToken(String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            // ???
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            // https://www.baeldung.com/java-http-request
            // https://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=");
            sb.append("&redirect_uri=");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonElement element = JsonParser.parseString(result);
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public HashMap<String, Object> getMemberInfo(String access_Token) {
        HashMap<String, Object> memberInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonElement element = JsonParser.parseString(result);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            // String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return memberInfo;
    }
}