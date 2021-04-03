package com.jiwoon.practicewebmvc.autowired;

import com.jiwoon.practicewebmvc.AutoAppConfig;
import com.jiwoon.practicewebmvc.discount.DiscountPolicy;
import com.jiwoon.practicewebmvc.member.Grade;
import com.jiwoon.practicewebmvc.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    /**
     * 의도적으로 해당 타입의 스프링 빈이 모두 필요한 경우가 있습니다.
     *
     * 예를 들어 할인 서비스를 제공하는 데 클라이언트가 할인의 종류 (rate, fix) 를 선택할 수 있다고 가정합니다.
     *
     * 스프링을 사용하면 소위 말하는 전략 패턴을 매우 간단하게 구현 가능합니다.
     *
     * Map, List 를 사용하면 같은 타입의 여러 빈을 모두 가져올 수 있습니다.
     *
     * DiscountService 는 discountCode 에 따라서 어떤 할인을 적용할 지 유연하게 적용 가능합니다.
     *
     */

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
