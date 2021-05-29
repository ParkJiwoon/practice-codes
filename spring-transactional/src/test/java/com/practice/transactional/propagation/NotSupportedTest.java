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

@DisplayName("NOT_SUPPORTED: non-transactional 상태로 실행하며 부모 트랜잭션이 존재하는 경우 일시 정지시킴")
@SpringBootTest
public class NotSupportedTest {

    private static final String NAME = "NOT_SUPPORTED";
    private static final Integer AGE = 23;

    @Autowired
    private ParentService service;

    @Autowired
    private MemberRepository repository;

    @BeforeEach
    void beforeClear() {
        repository.deleteAll();
    }

    @DisplayName("부모 트랜잭션이 있으면 그 트랜잭션을 일시 정지시킴")
    @Test
    void newSupportIfExist() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callTransaction(Propagation.NOT_SUPPORTED, NAME)).contains("ChildService.getNotSupported");

        assertThat(repository.findByName(NAME)).isPresent();
    }

    @DisplayName("부모 트랜잭션이 없어도 새로 생성하지 않음")
    @Test
    void newSupportIfNone() {
        repository.save(new Member(NAME, AGE));

        assertThat(service.callNoTransaction(Propagation.NOT_SUPPORTED, NAME)).contains("ChildService.getNotSupported");

        assertThat(repository.findByName(NAME)).isPresent();
    }
}
