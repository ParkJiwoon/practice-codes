package com.example.springwebfluxmongodb.controller;

import com.example.springwebfluxmongodb.entity.Cart;
import com.example.springwebfluxmongodb.entity.CartItem;
import com.example.springwebfluxmongodb.repository.CartRepository;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(
                Rendering.view("home") // 렌더링에 사용할 템플릿
                        .modelAttribute("items", itemRepository.findAll())
                        .modelAttribute("cart", cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                        .build()
        );
    }

    /**
     * My Cart 라는 장바구니를 찾아 새로 상품을 담거나 이미 있는 상품의 수량을 증가시킴
     *
     * @param id    장바구니에 담으려는 상품(Item) 의 id
     * @return      redirect 메세지
     */
    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart"))
                .flatMap(cart ->
                        cart.getCartItems().stream()
                                .filter(cartItem -> cartItem.getItem().getId().equals(id))
                                .findAny()
                                .map(cartItem -> {
                                    cartItem.increment();
                                    return Mono.just(cart);
                                })
                                .orElse(itemRepository.findById(id)
                                        .map(CartItem::new)
                                        .map(cartItem -> {
                                            cart.getCartItems().add(cartItem);
                                            return cart;
                                        })
                                ))
                .flatMap(cartRepository::save)
                .thenReturn("redirect:/");
    }
}
