package com.jiwoon.practicewebmvc.discount;

import com.jiwoon.practicewebmvc.annotation.MainDiscountPolicy;
import com.jiwoon.practicewebmvc.member.Grade;
import com.jiwoon.practicewebmvc.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy // @Qualifer 를 사용한 새로운 어노테이션 사용
//@Primary 여러 개의 빈이 있을 때 우선적으로 선택
public class RateDiscountPolicy implements DiscountPolicy {

    final static int DISCOUNT_RATE = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT_RATE * price / 100;
        } else {
            return 0;
        }
    }
}
