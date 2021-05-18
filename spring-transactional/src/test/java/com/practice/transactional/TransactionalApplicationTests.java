package com.practice.transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class TransactionalApplicationTests {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void contextLoads() {
    }

    // 현재 트랜잭션 이름 조회
    private void logCurrentTransactionName() {
        System.out.println("currentTransactionName : " + TransactionSynchronizationManager.getCurrentTransactionName());
    }

    // 영속성 컨텍스트에 올라가있는 지 조회
    private boolean isInPersistenceContext(Object entity) {
        return entityManager.contains(entity);
    }
}
