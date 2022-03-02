package com.module3.member.service;

import com.module3.member.model.Member;

import java.util.List;
import java.util.Optional;

public interface Memberservice {
    int addMember(Member member);
    int editMember(Long id, Member member);
    List<Member> findAllMember();
    Optional<Member> findMemberById(Long id);
    int deleteMember(Long id);
}
