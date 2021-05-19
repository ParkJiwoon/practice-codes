package com.practice.transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@DisplayName("@Transactional 안걸었을 경우 CRUD 테스트")
@SpringBootTest
public class NoTransactionalTest {

    private static final String NAME = "alice";
    private static final Integer AGE = 23;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private NoTransactionalService service;

    @Autowired
    private MemberRepository repository;

    @DisplayName("일정한 환경을 위해 @Transactional 대신 @beforeEach 로 DB 클리어")
    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("@Transactional 을 안걸어줘도 JpaRepository 의 구현체인 SimpleJpaRepository 에 내부적으로 걸려있어서 성공함")
    @Test
    void testCreate() {
        service.create(NAME, AGE);
        assertThat(repository.findAll()).isNotEmpty();
    }

    @DisplayName("@Transactional 을 걸지 않으면 Dirty Checking 은 실패")
    @Test
    void testDirtyChecking() {
        service.create(NAME, AGE);
        service.updateWithDirtyChecking(NAME, NAME + " updated");

        assertThat(repository.findByName(NAME)).isPresent();
        assertThat(repository.findByName(NAME + " updated")).isEmpty();
    }

    @DisplayName("@Transactional 안걸어두면 Entity 는 영속성 컨텍스트에 관리되지 않음")
    @Test
    void testPersistenceContext() {
        Member member = new Member(NAME, AGE);
        repository.save(member);

        assertThat(getCurrentTransactionName()).isNull();
        assertThat(isInPersistenceContext(member)).isFalse();
    }

    // 현재 트랜잭션 이름 조회
    private String getCurrentTransactionName() {
        return TransactionSynchronizationManager.getCurrentTransactionName();
    }

    // 영속성 컨텍스트에 올라가있는 지 조회
    private boolean isInPersistenceContext(Object entity) {
        return entityManager.contains(entity);
    }
}
