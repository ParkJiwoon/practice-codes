package com.jiwoon.practicewebmvc.beanfind;

import com.jiwoon.practicewebmvc.AppConfig;
import com.jiwoon.practicewebmvc.member.MemberService;
import com.jiwoon.practicewebmvc.member.MemoryMemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    // 인터페이스가 아닌 구현체로 조회할 수 있으나 좋지는 않음
    // 항상 구현체에 의존하지 말고 추상화에 의존하자
    @Test
    @DisplayName("이름 없이 구현체 타입으로 조회")
    void findBeanByType2() {
        MemoryMemberRepository memoryMemberRepository = ac.getBean(MemoryMemberRepository.class);
        Assertions.assertThat(memoryMemberRepository).isInstanceOf(MemoryMemberRepository.class);
    }

}
