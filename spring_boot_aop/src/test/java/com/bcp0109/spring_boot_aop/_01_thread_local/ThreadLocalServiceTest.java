package com.bcp0109.spring_boot_aop._01_thread_local;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ThreadLocalServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ThreadLocalServiceTest.class);

    private final ThreadLocalService service = new ThreadLocalService();

    @Test
    @DisplayName("두 쓰레드에서 동시에 같은 로직을 실행해도 쓰레드 로컬 때문에 동시성 보장")
    void threadLocal() throws InterruptedException {
        log.info("main start");

        Runnable userA = () -> service.logic("userA");
        Runnable userB = () -> service.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        Thread.sleep(1000);
        threadB.start();

        Thread.sleep(2000);
        log.info("main exit");
    }

}
