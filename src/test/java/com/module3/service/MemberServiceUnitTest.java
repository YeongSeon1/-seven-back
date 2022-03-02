package com.module3.service;

import com.module3.member.repository.MemberRepository;
import com.module3.member.service.Memberservice;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceUnitTest {

    @InjectMocks
    private Memberservice memberservice;

    @Mock
    private MemberRepository memberRepository;
}
