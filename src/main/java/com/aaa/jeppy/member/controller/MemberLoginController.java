package com.aaa.jeppy.member.controller;

import com.aaa.jeppy.member.dto.MemberCheckRegistedDTO;
import com.aaa.jeppy.member.service.CheckMemberRegistedService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class MemberLoginController {

    private final CheckMemberRegistedService checkMemberRegistedService;

    public MemberLoginController(CheckMemberRegistedService checkMemberRegistedService) {
        this.checkMemberRegistedService = checkMemberRegistedService;
    }

    @PostMapping("/check-login")
    public ResponseEntity<Void> memberIsRegistered(@RequestBody MemberCheckRegistedDTO memberCheckRegistedDTO) {

        boolean isRegistered = checkMemberRegistedService.isMemberRegistered(memberCheckRegistedDTO.getMemberEmail());

        if (isRegistered) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/google/register")
    public ResponseEntity<Void> registerGoogleMember(@RequestBody MemberCheckRegistedDTO memberCheckRegistedDTO) {
        System.out.println("registerMember: " + memberCheckRegistedDTO.getMemberEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/kakao/register")
    public ResponseEntity<Void> registerKakaoMember(@RequestBody MemberCheckRegistedDTO memberCheckRegistedDTO) {
        System.out.println("registerMember: " + memberCheckRegistedDTO.getMemberEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}