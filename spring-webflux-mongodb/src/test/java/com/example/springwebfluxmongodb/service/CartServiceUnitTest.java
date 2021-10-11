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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 *  @ExtendWith : 테스트 핸들러를 지정할 수 있는 JUnit5 의 API
 *  SpringExtension : 스프링에 특화된 테스트 기능을 사용할 수 있게 해줌
 */
@ExtendWith(SpringExtension.class)
public class CartServiceUnitTest {

    // CUT (class under test) : 테스트 대상 클래스
    private CartService cartService;

    // 여기서는 InventoryService 만 테스트하기 위해 나머지 클래스들은 가짜 인스턴스를 만든다
    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private CartRepository cartRepository;

    // @BeforeEach 는 모든 테스트 메소드 실행 전에 실행된다
    @BeforeEach
    void setUp() {
        // 테스트 데이터 정의
        Item item = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem cartItem = new CartItem(item);
        Cart cart = new Cart("My Cart", Collections.singletonList(cartItem));

        // 협력자 (가짜 인스턴스) 와의 상호작용 정의
        Mockito.when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
        Mockito.when(itemRepository.findById(anyString())).thenReturn(Mono.just(item));
        Mockito.when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(cart));

        cartService = new CartService(itemRepository, cartRepository);
    }

    /**
     * 리액터 코드는 구독을 해야 발생
     * 여기서는 StepVerifier 클래스가 구독을 담당
     */
    @DisplayName("addToCart 메소드로 비어 있는 Cart 에 Item 을 추가하면 CartItem 한 개를 만들어야 함")
    @Test
    void addItemToEmptyCartShouldProduceOneCartItem() {
        cartService.addItemToCart("My Cart", "item1")
                .as(StepVerifier::create)
                .expectNextMatches(cart -> {
                    // 수량은 1개
                    assertThat(cart.getCartItems())
                            .extracting(CartItem::getQuantity)
                            .containsExactlyInAnyOrder(1);

                    // BeforeEach 에서 추가한 Item 과 맞는지 비교
                    assertThat(cart.getCartItems())
                            .extracting(CartItem::getItem)
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));

                    // 이 지점까지 에러 없이 통과했다면 테스트 성공인 true 를 리턴
                    return true;
                })
                .verifyComplete();
    }

    @DisplayName("위 테스트 코드를 다른 방식으로 작성 - 책에서는 위 방식을 더 추천함 (테스트 대상 메소드가 먼저 드러나기 때문에)")
    @Test
    void alternativeWayToTest() {
        StepVerifier.create(
                cartService.addItemToCart("My Cart", "item1"))
                .expectNextMatches(cart -> {
                    // 수량은 1개
                    assertThat(cart.getCartItems())
                            .extracting(CartItem::getQuantity)
                            .containsExactlyInAnyOrder(1);

                    // BeforeEach 에서 추가한 Item 과 맞는지 비교
                    assertThat(cart.getCartItems())
                            .extracting(CartItem::getItem)
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));

                    // 이 지점까지 에러 없이 통과했다면 테스트 성공인 true 를 리턴
                    return true;
                })
                .verifyComplete();
    }
}
