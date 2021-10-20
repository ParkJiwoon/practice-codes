package com.example.springwebfluxmongodb.controller;

import com.example.springwebfluxmongodb.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;

@RestController
public class SpringAmqpItemController {

    private static final Logger log = LoggerFactory.getLogger(SpringAmqpItemController.class);

    private final AmqpTemplate template;

    public SpringAmqpItemController(AmqpTemplate template) {
        this.template = template;
    }

    @PostMapping("/mq/items")
    public Mono<ResponseEntity<?>> addNewItemUsingSpringAmqp(@RequestBody Mono<Item> item) {
        // RabbitTemplate 은 Blocking API 를 호출하기 때문에 subscribeOn 을 사용해서 다른 쓰레드에서 호출
        return item.subscribeOn(Schedulers.boundedElastic())
                .flatMap(content ->
                        Mono.fromCallable(() -> {
                            // convertAndSend(exchange, routingKey, message)
                            template.convertAndSend("hacking-spring-boot", "new-items-spring-amqp", content);
                            return ResponseEntity.created(URI.create("/items")).build();
                        })
                );
    }
}
