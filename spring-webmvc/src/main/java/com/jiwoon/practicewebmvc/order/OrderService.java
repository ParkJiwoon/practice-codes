package com.jiwoon.practicewebmvc.order;

import com.jiwoon.practicewebmvc.annotation.MainDiscountPolicy;
import com.jiwoon.practicewebmvc.discount.DiscountPolicy;
import com.jiwoon.practicewebmvc.member.Member;
import com.jiwoon.practicewebmvc.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderService(MemberRepository memberRepository,
                        @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
