package com.module3.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.module3.member.model.Member;
import com.module3.member.repository.MemberRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MemberControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

//    @BeforeEach
//    public void init() {
//        entityManager.createNativeQuery("ALTER TABLE member COLUMN id RESTART WITH 1").executeUpdate();
//    }

    @Autowired
    private MemberRepository memberRepository;

//    public void save_test() throws Exception {
//        Member member = new Member(null, "test", "M", "asd@naver.com", 1998);
//        String content = new ObjectMapper().writeValueAsString(member);
//
//        ResultActions resultAction = mockMvc.perform(post("/api/member/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content)
//                .accept(MediaType.APPLICATION_JSON));
//
//        resultAction
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.title").value("test"))
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void findAll_test() throws Exception {
        // given
        List<Member> members = new ArrayList<>();
        members.add(new Member(null, "test1", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test2", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test3", "M", "asd@naver.com", 1998));
        memberRepository.saveAll(members);

        // when
        ResultActions resultActions = mockMvc.perform(get("/api/member/getAll")
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.[0].name").value("test1"))
                .andExpect(jsonPath("$.[0].gender").value("M"))
                .andExpect(jsonPath("$.[0].userEmail").value("asd@naver.com"))
                .andExpect(jsonPath("$.[0].birthYear").value(1998))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findById_test() throws Exception {
        // given
        Long id = 2L;

        List<Member> members = new ArrayList<>();
        members.add(new Member(null, "test1", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test2", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test3", "M", "asd@naver.com", 1998));
        memberRepository.saveAll(members);

        // when
        ResultActions resultAction = mockMvc.perform(get("/api/member/find/{id}", id)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test2"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update_test() throws Exception {
        // given
        Long id = 2L;

        List<Member> members = new ArrayList<>();
        members.add(new Member(null, "test1", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test2", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test3", "M", "asd@naver.com", 1998));
        memberRepository.saveAll(members);

        Member member = new Member(null, "test4", "M", "asd@naver.com", 1998);
        String content = new ObjectMapper().writeValueAsString(member);

        // when
        ResultActions resultAction = mockMvc.perform(put("/api/member/edit/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2L"))
                .andExpect(jsonPath("$.name").value("test4"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delete_test() throws Exception {
        // given

        Long id = 1L;
        List<Member> members = new ArrayList<>();
        members.add(new Member(null, "test1", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test2", "M", "asd@naver.com", 1998));
        members.add(new Member(null, "test3", "M", "asd@naver.com", 1998));
        memberRepository.saveAll(members);

        // when
        ResultActions resultAction = mockMvc.perform(delete("/api/member/delete/{id}", id)
                .accept(MediaType.TEXT_PLAIN));
        // then
        resultAction
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        MvcResult requestResult = resultAction.andReturn();
        String result = requestResult.getResponse().getContentAsString();

        assertEquals("ok", result);
    }
}
