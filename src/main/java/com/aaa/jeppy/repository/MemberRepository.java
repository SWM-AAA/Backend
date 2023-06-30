package com.aaa.jeppy.repository;

import com.aaa.jeppy.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String memberEmail);
    
}
