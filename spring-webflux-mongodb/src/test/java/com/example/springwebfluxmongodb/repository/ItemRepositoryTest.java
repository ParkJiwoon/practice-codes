package com.example.springwebfluxmongodb.repository;

import com.example.springwebfluxmongodb.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;

/**
 *  @DataMongoTest : Spring Data MongoDB 활용에 초점을 둔 몽고디비 테스트 관련 기능 활성화
 *                   그 외에 @Component 어노테이션이 붙은 다른 빈 정의를 무시해서 테스트 속도 향상
 *                   @ExtendWith(SpringExtension.class) 을 포함하고 있기 때문에 JUnit 5 사용 가능
 */
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @DisplayName("Item 저장 성공")
    @Test
    void itemRepositorySavesItems() {
        Item newItem = new Item("name", "description", 1.99);

        repository.save(newItem)
                .as(StepVerifier::create)
                .expectNextMatches(item -> {
                    assertThat(item.getId()).isNotNull();
                    assertThat(item.getName()).isEqualTo("name");
                    assertThat(item.getDescription()).isEqualTo("description");
                    assertThat(item.getPrice()).isEqualTo(1.99);

                    return true;
                })
                .verifyComplete();
    }
}
