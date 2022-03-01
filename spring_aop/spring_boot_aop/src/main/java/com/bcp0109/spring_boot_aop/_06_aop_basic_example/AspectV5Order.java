package com.bcp0109.spring_boot_aop._06_aop_basic_example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

public class AspectV5Order {

    @Aspect
    @Order(2)
    public static class LogAspect {

        @Around("com.bcp0109.spring_boot_aop._06_aop_basic_example.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            System.out.println("[log] " + joinPoint.getSignature());    // join point 시그니처
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect {

        @Around("com.bcp0109.spring_boot_aop._06_aop_basic_example.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                System.out.println("[트랜잭션 시작] " + joinPoint.getSignature());
                Object result = joinPoint.proceed();
                System.out.println("[트랜잭션 커밋] " + joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                System.out.println("[트랜잭션 롤백] " + joinPoint.getSignature());
                throw e;
            } finally {
                System.out.println("[리소스 릴리즈] " + joinPoint.getSignature());
            }
        }
    }
}
