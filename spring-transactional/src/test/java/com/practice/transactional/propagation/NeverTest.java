package com.practice.transactional.propagation;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Propagation;

import static org.assertj.core.api.Assertions.*;

@DisplayName("NEVER: non-transactional 상태로 실행하며 부모 트랜잭션이 존재하는 경우 예외 발생")
@SpringBootTest
public class NeverTest {

    private static final String NAME = "NEVER";
    private static final Integer AGE = 23;

    @Autowired
    private ParentService service;

    @Autowired
    private MemberRepository repository;

    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("부모 트랜잭션이 존재하면 Exception 던짐")
    @Test
    void throwExceptionIfExists() {
        repository.save(new Member(NAME, AGE));

        assertThatExceptionOfType(IllegalTransactionStateException.class)
                .isThrownBy(() -> service.callTransaction(Propagation.NEVER, NAME))
                .withMessage("Existing transaction found for transaction marked with propagation 'never'");

        assertThat(repository.findByName(NAME)).isPresent();
    }

    @DisplayName("non-transactional 상태로 실행")
    @Test
    void createIfNone() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callNoTransaction(Propagation.NEVER, NAME)).contains("ChildService.getNever");

        assertThat(repository.findByName(NAME)).isPresent();
    }
}
