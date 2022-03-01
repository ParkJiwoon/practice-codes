package com.bcp0109.spring_boot_aop._06_aop_basic_example;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    // _05_aop_basic_example 패키지와 하위 패키지
    @Pointcut("execution(* com.bcp0109.spring_boot_aop._06_aop_basic_example..*(..))")
    public void allOrder() {}

    // 이름이 *Service 패턴인 모든 클래스
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
