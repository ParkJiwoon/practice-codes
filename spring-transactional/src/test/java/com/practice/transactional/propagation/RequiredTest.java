package com.practice.transactional.propagation;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("REQUIRED: 기본값이며 부모 트랜잭션이 존재할 경우 참여하고 없는 경우 새 트랜잭션을 시작")
@SpringBootTest
public class RequiredTest {

    private static final String NAME = "REQUIRED";
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

        assertThat(service.callTransaction(Propagation.REQUIRED, NAME)).contains("ParentService.callTransaction");

        assertThat(repository.findByName(NAME)).isEmpty();
    }

    @DisplayName("부모 트랜잭션이 없으면 새 트랜잭션 시작")
    @Test
    void newSupportIfNone() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callNoTransaction(Propagation.REQUIRED, NAME)).contains("ChildService.getRequired");

        assertThat(repository.findByName(NAME)).isEmpty();
    }
}
