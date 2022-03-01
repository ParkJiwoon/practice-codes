package com.bcp0109.spring_boot_aop._05_aop_basic_example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectV2 {

    /**
     * @Pointcut 어노테이션을 사용해서 별도로 표현식 분리
     */

    // _05_aop_basic_example 패키지와 하위 패키지
    @Pointcut("execution(* com.bcp0109.spring_boot_aop._05_aop_basic_example..*(..))")
    private void allOrder() {
        /*
         * pointcut signature: 메서드 이름과 파라미터를 합쳐서 포인트컷 시그니쳐라고 함
         * 메서드의 반환 타입은 void 여야함
         * 코드 내용은 비워둠
         * 다른 Aspect 에서 사용하려면 public 으로 선언하면 됨
         */
    }

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[log] " + joinPoint.getSignature());    // join point 시그니처
        return joinPoint.proceed();
    }
}
