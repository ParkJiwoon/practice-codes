package com.jiwoon.practicewebmvc.discount;

import com.jiwoon.practicewebmvc.member.Grade;
import com.jiwoon.practicewebmvc.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    final static int DISCOUNT_PRICE = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT_PRICE;
        } else {
            return 0;
        }
    }
}
