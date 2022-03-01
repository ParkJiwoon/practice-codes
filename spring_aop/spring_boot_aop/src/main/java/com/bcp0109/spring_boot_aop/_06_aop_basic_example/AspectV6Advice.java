package com.bcp0109.spring_boot_aop._06_aop_basic_example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectV6Advice {

    /**
     *
     */
    @Around("com.bcp0109.spring_boot_aop._06_aop_basic_example.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before
            System.out.println("[트랜잭션 시작] " + joinPoint.getSignature());
            Object result = joinPoint.proceed();
            System.out.println("[트랜잭션 커밋] " + joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            System.out.println("[트랜잭션 롤백] " + joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            System.out.println("[리소스 릴리즈] " + joinPoint.getSignature());
        }
    }

    @Before("com.bcp0109.spring_boot_aop._06_aop_basic_example.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("[before] " + joinPoint.getSignature());
    }

    @AfterReturning(value = "com.bcp0109.spring_boot_aop._06_aop_basic_example.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        System.out.println("[return] " + joinPoint.getSignature() + " return=" + result);
    }

    @AfterThrowing(value = "com.bcp0109.spring_boot_aop._06_aop_basic_example.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("[ex] " + joinPoint.getSignature() + " message=" + ex.getMessage());
    }

    @After("com.bcp0109.spring_boot_aop._06_aop_basic_example.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("[after] " + joinPoint.getSignature());
    }
}
