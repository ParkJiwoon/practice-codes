package com.example.springwebfluxmongodb.service;

import com.example.springwebfluxmongodb.entity.Cart;
import com.example.springwebfluxmongodb.entity.CartItem;
import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.CartRepository;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
public class BlockHoundIntegrationTest {

    private BlockingCartService cartService;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 정의
        Item item = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem cartItem = new CartItem(item);
        Cart cart = new Cart("My Cart", Collections.singletonList(cartItem));

        // 협력자 (가짜 인스턴스) 와의 상호작용 정의
        Mockito.when(cartRepository.findById(anyString()))
                // Mono.empty() 를 반환하면 리액터가 감지해서 최적화를 위해 코드를 검사하지 않고 지나감
                // 따라서 hide() 를 호출해서 MonoEmpty 를 숨겨서 리액터 최적화 루틴에 걸리지 않게 한다
                .thenReturn(Mono.<Cart>empty().hide());

        Mockito.when(itemRepository.findById(anyString())).thenReturn(Mono.just(item));
        Mockito.when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(cart));

        cartService = new BlockingCartService(itemRepository, cartRepository);
    }

    /**
     * BlockHound 로 검증하려면 리액터 스레드 안에서 실행해야하기 때문에 Mono.delay() 사용
     */
    @DisplayName("BlockHound 로 블로킹 호출을 검사한다")
    @Test
    void blockHoundShouldTrapBlockingCall() {
        Mono.delay(Duration.ofSeconds(1))
                .flatMap(tick -> cartService.addItemToCartBlocking("My Cart", "item1"))
                .as(StepVerifier::create)
                .verifyErrorMatches(throwable -> {
                    assertThat(throwable).hasMessageContaining("block()/blockFirst()/blockLast() are blocking");
                    return true;
                });
    }
}
