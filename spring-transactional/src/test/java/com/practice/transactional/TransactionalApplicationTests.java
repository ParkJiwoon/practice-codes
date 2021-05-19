package com.practice.transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class TransactionalApplicationTests {

    @Test
    void contextLoads() {
    }

}
