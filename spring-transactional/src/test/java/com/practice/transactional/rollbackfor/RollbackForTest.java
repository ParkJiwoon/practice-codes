package com.practice.transactional.rollbackfor;

import com.practice.transactional.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RollbackForTest {

    private static final String NAME = "alice";
    private static final Integer AGE = 23;

    @Autowired
    private RollbackForService service;

    @Autowired
    private MemberRepository repository;

    @DisplayName("일정한 환경을 위해 @Transactional 대신 @beforeEach 로 DB 클리어")
    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("rollbackFor 기본값으로 RuntimeException 존재")
    @Test
    void testDefaultRuntimeException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> service.throwRuntimeException(NAME, AGE));

        assertThat(repository.findByName(NAME)).isEmpty();
    }

    @DisplayName("rollbackFor 기본값으로 Error 존재")
    @Test
    void testDefaultError() {
        assertThatExceptionOfType(Error.class)
                .isThrownBy(() -> service.throwError(NAME, AGE));

        assertThat(repository.findByName(NAME)).isEmpty();
    }

    @DisplayName("rollbackFor 대상으로 지정되지 않은 Checked Exception 은 롤백 안함")
    @Test
    void testCheckedException() {
        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> service.throwIOException(NAME, AGE));

        assertThat(repository.findByName(NAME)).isPresent();
    }

    @DisplayName("rollbackFor 대상으로 지정된 Checked Exception 은 롤백 수행")
    @Test
    void testRollbackForIOException() {
        assertThatExceptionOfType(IOException.class)
                .isThrownBy(() -> service.throwRollbackForIOException(NAME, AGE));

        assertThat(repository.findByName(NAME)).isEmpty();
    }

    @DisplayName("rollbackFor 옵션을 주어도 RuntimeException 이나 Error 발생 시 롤백하는 건 변하지 않음")
    @Test
    void testThrowRuntimeExceptionTest() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> service.throwRuntimeExceptionTest(NAME, AGE));

        assertThat(repository.findByName(NAME)).isEmpty();
    }
}
