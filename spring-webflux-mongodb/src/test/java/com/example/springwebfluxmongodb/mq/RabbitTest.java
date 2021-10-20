package com.example.springwebfluxmongodb.mq;

import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;

// webEnvironment 설정이 없으면 WebTestClient 빈을 못찾는 이슈가 있음..
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient     // WebTestClient 를 사용하기 위해 필요
@Testcontainers                 // JUnit 5 에서 제공하는 어노테이션이며 테스트 컨테이너를 테스트에 사용할 수 있게 해줌
@ContextConfiguration           // 지정한 클래스를 테스트 실행 전에 먼저 애플리케이션 컨텍스트에 로딩해줌
public class RabbitTest {

    /**
     * 테스트에 사용할 RabbitMQ 인스턴스를 관리
     * 생성자 파라미터로 https://hub.docker.com/_/rabbitmq 에서 도커 이미지 선택
     */
    @Container
    private static final RabbitMQContainer container =
            new RabbitMQContainer("rabbitmq:3.8-management-alpine");

    @Autowired
    private WebTestClient client;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 환경설정 내용을 Environment 에 동적으로 추가
     * 여기서는 테스트 컨테이너에서 실행한 RabbitMQ 의 호스트 이름과 포트 번호를 가져옴
     */
    @DynamicPropertySource
    public static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", container::getContainerIpAddress);
        registry.add("spring.rabbitmq.port", container::getAmqpPort);
    }

    @BeforeEach
    void setUp() {
        itemRepository.deleteAll().block();
    }

    @DisplayName("Item 두 개를 순차적으로 저장하면 성공")
    @Test
    void verifyMessagingThroughAmqp() throws InterruptedException {
        client.post().uri("/mq/items")
                .bodyValue(new Item("Alf alarm clock", "nothing important", 19.99))
                .exchange()
                .expectStatus().isCreated()
                .expectBody();

        /**
         * 지금까지는 StepVerifier 를 사용해서 비동기 처리 흐름을 쉽게 테스트 하고 지연 효과를 낼 수도 있었음
         * 하지만 이 테스트에서는 RabbitVerifier 같은 게 없어서 직접 슬립 메소드를 사용해야 함
         * POST 요청을 하기 전에 잠시 지연시켜서 Item 이 순서대로 저장도게 함
         */
        Thread.sleep(1500L);

        client.post().uri("/mq/items")
                .bodyValue(new Item("Smurf TV tray", "nothing important", 29.99))
                .exchange()
                .expectStatus().isCreated()
                .expectBody();

        // 위 두 요청이 전부 처리될때까지 2 초간 대기
        Thread.sleep(2000L);

        itemRepository.findAll()
                .as(StepVerifier::create)
                .expectNextMatches(item -> {
                    assertThat(item.getName()).isEqualTo("Alf alarm clock");
                    assertThat(item.getDescription()).isEqualTo("nothing important");
                    assertThat(item.getPrice()).isEqualTo(19.99);
                    return true;
                })
                .expectNextMatches(item -> {
                    assertThat(item.getName()).isEqualTo("Smurf TV tray");
                    assertThat(item.getDescription()).isEqualTo("nothing important");
                    assertThat(item.getPrice()).isEqualTo(29.99);
                    return true;
                })
                .verifyComplete();
    }
}
