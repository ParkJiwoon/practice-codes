package com.bcp0109.spring_boot_aop._07_aop_advanced_example;

import com.bcp0109.spring_boot_aop._07_aop_advanced_example.annotation.Retry;
import com.bcp0109.spring_boot_aop._07_aop_advanced_example.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 5번에 1번 실패하는 요청
     */
    @Trace
    @Retry(value = 4)
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
