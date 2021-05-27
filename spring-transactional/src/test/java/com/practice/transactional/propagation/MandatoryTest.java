package com.practice.transactional.propagation;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
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

    @DisplayName("부모 트랜잭션이 존재할 경우 참여")
    @Test
    void supportIfExist() {
        String name = NAME + "1";
        repository.save(new Member(name, AGE));

        assertThat(service.callTransaction(Propagation.MANDATORY, name)).contains("ParentService.callTransaction");

        assertThat(repository.findByName(name)).isEmpty();
    }

    @DisplayName("부모 트랜잭션이 없으면 예외 발생")
    @Test
    void throwExceptionIfNone() {
        String name = NAME + "2";
        repository.save(new Member(name, AGE));

        assertThatExceptionOfType(IllegalTransactionStateException.class)
                .isThrownBy(() -> service.callNoTransaction(Propagation.MANDATORY, name))
                .withMessage("No existing transaction found for transaction marked with propagation 'mandatory'");

        assertThat(repository.findByName(name)).isPresent();
    }
}
