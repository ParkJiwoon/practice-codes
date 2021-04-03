package com.jiwoon.practicewebmvc;

import com.jiwoon.practicewebmvc.discount.DiscountPolicy;
import com.jiwoon.practicewebmvc.discount.FixDiscountPolicy;
import com.jiwoon.practicewebmvc.discount.RateDiscountPolicy;
import com.jiwoon.practicewebmvc.member.MemberService;
import com.jiwoon.practicewebmvc.member.MemoryMemberRepository;
import com.jiwoon.practicewebmvc.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
