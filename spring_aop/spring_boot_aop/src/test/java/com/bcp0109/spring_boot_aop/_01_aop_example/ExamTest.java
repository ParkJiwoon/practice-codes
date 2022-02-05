package com.bcp0109.spring_boot_aop._01_aop_example;

import com.bcp0109.spring_boot_aop._01_aop_example.aop.RetryAspect;
import com.bcp0109.spring_boot_aop._01_aop_example.aop.TraceAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {

    @Autowired
    private ExamService examService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            System.out.println("client request i = " + i);
            examService.request("data" + i);
        }
    }
}
