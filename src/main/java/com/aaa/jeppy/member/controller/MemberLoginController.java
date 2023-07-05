package com.aaa.jeppy.member.controller;

import com.aaa.jeppy.member.dto.request.MemberCheckRegistedRequestDTO;
import com.aaa.jeppy.member.service.MemberAuthServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class MemberLoginController {

    private final MemberAuthServiceImpl MemberAuthServiceImpl;

    public MemberLoginController(MemberAuthServiceImpl MemberAuthServiceImpl) {
        this.MemberAuthServiceImpl = MemberAuthServiceImpl;
    }

    @PostMapping("/check-login")
    public ResponseEntity<Void> memberIsRegistered(@RequestBody MemberCheckRegistedRequestDTO MemberCheckRegistedRequestDTO) {

        boolean isRegistered = MemberAuthServiceImpl.isMemberRegistered(MemberCheckRegistedRequestDTO.getMemberEmail());

        if (isRegistered) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/google/register")
    public ResponseEntity<Void> registerGoogleMember(@RequestBody MemberCheckRegistedRequestDTO MemberCheckRegistedRequestDTO) {
        System.out.println("registerMember: " + MemberCheckRegistedRequestDTO.getMemberEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/kakao/register")
    public ResponseEntity<Void> registerKakaoMember(@RequestBody MemberCheckRegistedRequestDTO MemberCheckRegistedRequestDTO) {
        System.out.println("registerMember: " + MemberCheckRegistedRequestDTO.getMemberEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}