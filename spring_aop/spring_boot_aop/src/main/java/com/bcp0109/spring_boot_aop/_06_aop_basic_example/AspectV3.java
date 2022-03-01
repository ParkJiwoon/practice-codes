package com.bcp0109.spring_boot_aop._06_aop_basic_example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectV3 {

    // _05_aop_basic_example 패키지와 하위 패키지
    @Pointcut("execution(* com.bcp0109.spring_boot_aop._06_aop_basic_example..*(..))")
    private void allOrder() {}

    // 이름이 *Service 패턴인 모든 클래스
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[log] " + joinPoint.getSignature());    // join point 시그니처
        return joinPoint.proceed();
    }

    /**
     * 포인트컷 두개 동시에 적용
     * &&(AND), ||(OR), !(NOT) 3가지 조합 가능
     * _05_aop_basic_example 패키지와 하위 패키지면서 이름이 *Service 인 것을 대상으로 함
     * doTransaction() 은 OrderService 에만 적용됨
     * doLog() 는 둘다 적용됨
     */
    @Around("allOrder() && allService()")
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
