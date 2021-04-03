package com.jiwoon.practicewebmvc.member;

import com.jiwoon.practicewebmvc.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    @DisplayName("Member 가입하기")
    void signup() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.signup(member);
        Member member1 = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(member1);
    }
}