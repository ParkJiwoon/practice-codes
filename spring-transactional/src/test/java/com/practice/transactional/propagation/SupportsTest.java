package com.practice.transactional.propagation;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SUPPORTS: 부모 트랜잭션이 존재할 경우 참여하고 없는 경우 non-transactional 상태로 실행")
@SpringBootTest
public class SupportsTest {

    private static final String NAME = "SUPPORTS";
    private static final Integer AGE = 23;

    @Autowired
    private ParentService service;

    @Autowired
    private MemberRepository repository;

    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("부모 트랜잭션이 존재하면 참여")
    @Test
    void supportIfExist() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callTransaction(Propagation.SUPPORTS, NAME)).contains("ParentService.callTransaction");

        assertThat(repository.findByName(NAME)).isEmpty();
    }

    @DisplayName("부모 트랜잭션이 없으면 Non-transactional")
    @Test
    void noTransactionIfNone() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callNoTransaction(Propagation.SUPPORTS, NAME)).contains("ChildService.getSupports");

        assertThat(repository.findByName(NAME)).isPresent();
    }
}
