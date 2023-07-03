package com.aaa.jeppy.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaa.jeppy.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String memberEmail);
    
}
