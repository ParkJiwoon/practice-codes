package com.bcp0109.spring_boot_aop._06_aop_advanced_example;

import com.bcp0109.spring_boot_aop._06_aop_advanced_example.annotation.Trace;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
