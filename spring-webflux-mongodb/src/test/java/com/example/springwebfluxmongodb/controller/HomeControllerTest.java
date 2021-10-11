package com.example.springwebfluxmongodb.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.thymeleaf.TemplateEngine;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

// @SpringBootTest : @SpringBootApplication 이 붙은 클래스를 찾아서 내장 컨테이너를 실행함
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class HomeControllerTest {

    @Autowired
    private WebTestClient client;

    @DisplayName("Home 페이지에는 add API 가 존재")
    @Test
    void homePageContainAddApi() {
        client.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.TEXT_HTML)
                .expectBody(String.class)
                .consumeWith(exchangeResult -> {
                    assertThat(exchangeResult.getResponseBody()).contains("<form method=\"post\" action=\"/add");
                });
    }
}
