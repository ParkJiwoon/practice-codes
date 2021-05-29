package com.practice.transactional.propagation;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.NestedTransactionNotSupportedException;
import org.springframework.transaction.annotation.Propagation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("NESTED: 부모 트랜잭션과는 별개의 중첩된 트랜잭션을 만듬")
@SpringBootTest
public class NestedTest {
    /*
     * `NESTED`:
     *   - 부모 트랜잭션과는 별개의 중첩된 트랜잭션을 만듬
     *   - 부모 트랜잭션의 커밋과 롤백에는 영향을 받지만 자신의 커밋과 롤백은 부모 트랜잭션에게 영향을 주지 않음
     *   - 부모 트랜잭션이 없는 경우 새로운 트랜잭션을 만듬 (`REQUIRED` 와 동일)
     *   - DB 가 SAVEPOINT 를 지원해야 사용 가능 (Oracle)
     *   - `JpaTransactionManager` 에서는 지원하지 않음
     */

    private static final String NAME = "NESTED";
    private static final Integer AGE = 23;

    @Autowired
    private ParentService service;

    @Autowired
    private MemberRepository repository;

    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("DB 가 SAVEPOINT 를 지원하지 않아서 에러")
    @Test
    void throwExceptionIfExists() {
        repository.save(new Member(NAME, AGE));

        assertThatExceptionOfType(NestedTransactionNotSupportedException.class)
                .isThrownBy(() -> service.callTransaction(Propagation.NESTED, NAME))
                .withMessage("JpaDialect does not support savepoints - check your JPA provider's capabilities");

        assertThat(repository.findByName(NAME)).isPresent();
    }

    @DisplayName("부모 트랜잭션이 없는 경우 새로운 트랜잭션을 만듬 (`REQUIRED` 와 동일)")
    @Test
    void newSupportIfNone() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callNoTransaction(Propagation.NESTED, NAME)).contains("ChildService.getNested");

        assertThat(repository.findByName(NAME)).isEmpty();
    }
}
