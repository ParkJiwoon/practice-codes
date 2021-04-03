package com.jiwoon.practicewebmvc;

import com.jiwoon.practicewebmvc.member.Grade;
import com.jiwoon.practicewebmvc.member.Member;
import com.jiwoon.practicewebmvc.member.MemberService;
import com.jiwoon.practicewebmvc.order.Order;
import com.jiwoon.practicewebmvc.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.signup(member);

        Order order = orderService.createOrder(memberId, "itemA", 200000);

        System.out.println("order = " + order);
    }
}
