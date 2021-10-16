package com.example.springwebfluxmongodb.controller;

import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import com.example.springwebfluxmongodb.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest(ApiItemController.class)
@AutoConfigureRestDocs  // Spring Rest Docs 사용에 필요한 내용을 자동으로 설정
public class ApiItemControllerDocumentationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private InventoryService inventoryService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    void findingAllItems() {
        when(itemRepository.findAll()).thenReturn(
                Flux.just(new Item("item-1", "Alf alarm clock", "nothing I really need", 19.99))
        );

        webTestClient.get().uri("/api/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                // 자동 생성된 문서는 findAll 디렉터리에 저장
                .consumeWith(document("findAll", preprocessResponse(prettyPrint())));
    }

    @Test
    void postNewItem() {
        when(itemRepository.save(any())).thenReturn(Mono.just(new Item("1", "Alf alarm clock", "nothing important", 19.99)));

        webTestClient.post().uri("/api/items")
                .bodyValue(new Item("Alf alarm clock", "nothing important", 19.99))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                // 자동 생성된 문서는 post-new-item 디렉터리에 저장
                .consumeWith(document("post-new-item", preprocessResponse(prettyPrint())));
    }
}
