package com.example.springwebfluxmongodb.service;

import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SpringAmqpItemService {

    private static final Logger log = LoggerFactory.getLogger(SpringAmqpItemService.class);

    private final ItemRepository itemRepository;

    public SpringAmqpItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // @RabbitListener 붙은 메소드는 Spring AMQP 메시지 리스너로 등록되어 메시지를 소비할 수 있음
    @RabbitListener(
            ackMode = "MANUAL",
            bindings = @QueueBinding(
                    // 큐 바인딩. 아무런 이름을 지정하지 않으면 임의의 지속성 없는 익명 큐 생성
                    value = @Queue,
                    // 큐와 연결될 Exchange
                    exchange = @Exchange("hacking-spring-boot"),
                    // Routing Key
                    key = "new-items-spring-amqp"
            )
    )
    public Mono<Void> processNewItemsViaSpringAmqp(Item item) {
        // RabbitMQ 메시지에 들어있던 Item 값
        log.debug("Consuming => " + item);
        return itemRepository.save(item).then();
    }
}
