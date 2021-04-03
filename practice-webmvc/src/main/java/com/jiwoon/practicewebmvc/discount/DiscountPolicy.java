package com.jiwoon.practicewebmvc.discount;

import com.jiwoon.practicewebmvc.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
