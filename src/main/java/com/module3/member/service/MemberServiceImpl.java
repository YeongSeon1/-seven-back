package com.module3.member.service;

import com.module3.member.model.Member;
import com.module3.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements Memberservice{

    private final MemberRepository memberRepository;

    @Override
    public int addMember(Member member) {
        Member member1;
        try {
            if (member.getId() == null) {
                member1 = Member.builder()
                        .id(null)
                        .name(member.getName())
                        .gender(member.getGender())
                        .userEmail(member.getUserEmail())
                        .birthYear(member.getBirthYear())
                        .build();
                memberRepository.save(member1);
            }
            return 1;
        }catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public int editMember(Long id, Member member) {
        Member member1 = findMemberById(id).get();
        try {
            member1.setName(member.getName());
            member1.setUserEmail(member.getUserEmail());
            member1.setBirthYear(member.getBirthYear());
            member1.setGender(member.getGender());
            memberRepository.save(member1);
            return 1;
            } catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Member> findAllMember() {
        try{
            return memberRepository.findAll();
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        try{
            return Optional.ofNullable(memberRepository.findById(id)).get();
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteMember(Long id) {
        try{
            if(findMemberById(id) != null){
                memberRepository.deleteById(id);
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return 0;
        }
    }
}
