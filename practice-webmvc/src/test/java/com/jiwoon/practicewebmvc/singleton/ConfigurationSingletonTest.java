package com.jiwoon.practicewebmvc.singleton;

import com.jiwoon.practicewebmvc.AppConfig;
import com.jiwoon.practicewebmvc.member.MemberRepository;
import com.jiwoon.practicewebmvc.member.MemberService;
import com.jiwoon.practicewebmvc.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
}
