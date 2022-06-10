package com.bcp0109.spring_boot_aop._04_proxy_factory;

import com.bcp0109.spring_boot_aop._04_proxy_factory.advice.TimeAdvice;
import com.bcp0109.spring_boot_aop._04_proxy_factory.pointcut.MyPointcut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class AdvisorTest {

    @Test
    @DisplayName("Pointcut 사용 예제")
    void advisorTest1() {
        StoreService target = new StoreService();

        // target 인스턴스 정보로 프록시 생성 (인터페이스 존재 - JDK, 구체 클래스만 존재 - CGLIB)
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 생성자를 통해 하나의 포인트컷과 하나의 어드바이스를 넣어주면 됨
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());

        // 프록시가 사용할 부가 기능 추가
        proxyFactory.addAdvisor(advisor);

        // 프록시 객체 생성
        StoreService proxy = (StoreService) proxyFactory.getProxy();

        // 프록시 호출
        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("직접 만든 MyPointcut 을 적용해서 save 메서드에만 프록시 적용")
    void advisorTest2() {
        StoreService target = new StoreService();

        // target 인스턴스 정보로 프록시 생성 (인터페이스 존재 - JDK, 구체 클래스만 존재 - CGLIB)
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 생성자를 통해 하나의 포인트컷과 하나의 어드바이스를 넣어주면 됨
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());

        // 프록시가 사용할 부가 기능 추가
        proxyFactory.addAdvisor(advisor);

        // 프록시 객체 생성
        StoreService proxy = (StoreService) proxyFactory.getProxy();

        // 프록시 호출
        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("스프링이 제공하는 포인트컷")
    void advisorTest3() {
        StoreService target = new StoreService();

        // target 인스턴스 정보로 프록시 생성 (인터페이스 존재 - JDK, 구체 클래스만 존재 - CGLIB)
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // 스프링에서 제공하는 포인트컷으로 save 메서드에만 프록시 적용
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save");

        // 생성자를 통해 하나의 포인트컷과 하나의 어드바이스를 넣어주면 됨
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());

        // 프록시가 사용할 부가 기능 추가
        proxyFactory.addAdvisor(advisor);

        // 프록시 객체 생성
        StoreService proxy = (StoreService) proxyFactory.getProxy();

        // 프록시 호출
        proxy.save();
        proxy.find();
    }
}
