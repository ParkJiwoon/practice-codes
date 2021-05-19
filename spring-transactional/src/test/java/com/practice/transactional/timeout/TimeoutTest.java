package com.practice.transactional.timeout;

import com.practice.transactional.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TimeoutTest {

    private static final String NAME = "alice";
    private static final Integer AGE = 23;

    @Autowired
    private TimeoutService service;

    @Autowired
    private MemberRepository repository;

    @DisplayName("일정한 환경을 위해 @Transactional 대신 @beforeEach 로 DB 클리어")
    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("2 초로 타임아웃 걸어두고 3 초 지나면 JpaSystemException 발생해서 롤백")
    @Test
    void testTimeout2Seconds3Sleep() {
        assertThatExceptionOfType(JpaSystemException.class)
                .isThrownBy(() -> service.timeout_2second_3sleep(NAME, AGE));

        assertThat(repository.findAll()).isEmpty();
    }

    @DisplayName("3 초로 타임아웃 걸어두고 1 초만 지났으면 정상 수행")
    @Test
    void testTimeout2Seconds1Sleep() throws InterruptedException {
        service.timeout_2second_1sleep(NAME, AGE);
        assertThat(repository.findAll()).isNotEmpty();
    }

    @DisplayName("timeout 이 발생할 때 noRollbackFor 에 JpaSystemException 를 지정해도 롤백 처리됨")
    @Test
    void testTimeoutAndNoRollbackFor() {
        assertThatExceptionOfType(JpaSystemException.class)
                .isThrownBy(() -> service.timeoutAndNoRollbackFor(NAME, AGE));

        assertThat(repository.findAll()).isEmpty();
    }
}
