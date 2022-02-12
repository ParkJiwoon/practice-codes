package com.bcp0109.spring_boot_aop._01_thread_local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalService {

    private static final Logger log = LoggerFactory.getLogger(ThreadLocalService.class);
    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", nameStore.get());
        return nameStore.get();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
