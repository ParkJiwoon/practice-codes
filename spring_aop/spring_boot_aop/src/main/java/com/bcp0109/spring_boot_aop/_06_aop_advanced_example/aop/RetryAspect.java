package com.bcp0109.spring_boot_aop._06_aop_advanced_example.aop;

import com.bcp0109.spring_boot_aop._06_aop_advanced_example.annotation.Retry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class RetryAspect {

    /**
     * 메서드 호출에서 예외 발생 시 재시도 하는 AOP
     * 파라미터로 받으면 대체 @Around("@annotation") 에 패키지 경로를 전부 입력하지 않아도 됨
     * 파라미터 경로로 알아서 추적해줌
     *
     * @param joinPoint 프록시 호출을 위한 joinPoint
     * @param retry     재시도 값을 갖고 있는 Annotation
     * @return          결과
     */
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        System.out.println("[trace] " + joinPoint.getSignature() + ", retry=" + retry);

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                System.out.println("[retry] try count=" + retryCount + "/" + maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }

        assert exceptionHolder != null;
        throw exceptionHolder;
    }
}
