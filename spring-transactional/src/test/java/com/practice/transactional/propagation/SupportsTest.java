package com.practice.transactional.propagation;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
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

    @DisplayName("부모 트랜잭션이 존재하면 참여")
    @Test
    void supportIfExist() {
        String name = NAME + "1";
        repository.save(new Member(name, AGE));

        assertThat(service.callTransaction(Propagation.SUPPORTS, name)).contains("ParentService.callTransaction");

        assertThat(repository.findByName(name)).isEmpty();
    }

    @DisplayName("부모 트랜잭션이 없으면 Non-transactional")
    @Test
    void noTransactionIfNone() {
        String name = NAME + "2";
        repository.save(new Member(name, AGE));

        assertThat(service.callNoTransaction(Propagation.SUPPORTS, name)).contains("ChildService.getSupports");

        assertThat(repository.findByName(name)).isPresent();
    }
}
