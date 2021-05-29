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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("MANDATORY: 부모 트랜잭션이 있으면 참여하고 없으면 예외 발생")
@SpringBootTest
public class MandatoryTest {

    private static final String NAME = "MANDATORY";
    private static final Integer AGE = 23;

    @Autowired
    private ParentService service;

    @Autowired
    private MemberRepository repository;

    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("부모 트랜잭션이 존재할 경우 참여")
    @Test
    void supportIfExist() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callTransaction(Propagation.MANDATORY, NAME)).contains("ParentService.callTransaction");

        assertThat(repository.findByName(NAME)).isEmpty();
    }

    @DisplayName("부모 트랜잭션이 없으면 예외 발생")
    @Test
    void throwExceptionIfNone() {
        repository.save(new Member(NAME, AGE));

        assertThatExceptionOfType(IllegalTransactionStateException.class)
                .isThrownBy(() -> service.callNoTransaction(Propagation.MANDATORY, NAME))
                .withMessage("No existing transaction found for transaction marked with propagation 'mandatory'");

        assertThat(repository.findByName(NAME)).isPresent();
    }
}
