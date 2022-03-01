package com.bcp0109.spring_boot_aop._05_aop_basic_example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectV1 {

    /**
     * 기본적인 AOP 적용 방법
     */

    // com.bcp0109.spring_boot_aop._05_aop_basic_example 패키지 하위 클래스에 모두 적용
    @Around("execution(* com.bcp0109.spring_boot_aop._05_aop_basic_example..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[log] " + joinPoint.getSignature());    // join point 시그니처
        return joinPoint.proceed();
    }
}
