package com.practice.transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("@Transactional 에서 INSERT 쿼리 정상적으로 호출")
    void testCreate() {
        memberService.create("woody", 23);
        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(1);
        assertThat(members.get(0).getName()).isEqualTo("woody");
        assertThat(members.get(0).getAge()).isEqualTo(23);
    }

    @Test
    @DisplayName("@Transactional(readOnly = true) 에서 INSERT 쿼리 무시됨")
    void testCreateWithReadOnlyTransactional() {
        memberService.createWithReadOnlyTransactional("woody", 23);
        List<Member> members = memberRepository.findAll();

        assertThat(members).isEmpty();
    }
}
