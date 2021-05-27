package com.practice.transactional.propagation;

import com.practice.transactional.Member;
import com.practice.transactional.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("REQUIRES_NEW: 부모 트랜잭션을 무시하고 무조건 새로운 트랜잭션 생성")
@SpringBootTest
public class RequiresNewTest {

    private static final String NAME = "REQUIRES_NEW";
    private static final Integer AGE = 23;

    @Autowired
    private ParentService service;

    @Autowired
    private MemberRepository repository;

    @DisplayName("부모 트랜잭션이 있어도 무시하고 새로운 트랜잭션 생성")
    @Test
    void newSupportIfExist() {
        String name = NAME + "1";
        repository.save(new Member(name, AGE));

        assertThat(service.callTransaction(Propagation.REQUIRES_NEW, name)).contains("ChildService.getRequiresNew");

        assertThat(repository.findByName(name)).isEmpty();
    }

    @DisplayName("부모 트랜잭션이 없으면 새 트랜잭션 시작")
    @Test
    void newSupportIfNone() {
        String name = NAME + "2";
        repository.save(new Member(name, AGE));

        assertThat(service.callNoTransaction(Propagation.REQUIRES_NEW, name)).contains("ChildService.getRequiresNew");

        assertThat(repository.findByName(name)).isEmpty();
    }
}
