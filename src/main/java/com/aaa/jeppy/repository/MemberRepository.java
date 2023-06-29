package com.aaa.jeppy.repository;

import com.aaa.jeppy.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmailAndProvider(String email, Member.SocialProvider provider);
    
}
