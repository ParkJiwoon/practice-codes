package com.example.springwebfluxmongodb.service;

import com.example.springwebfluxmongodb.entity.Cart;
import com.example.springwebfluxmongodb.entity.CartItem;
import com.example.springwebfluxmongodb.repository.CartRepository;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<String> addToCart(String cartId, String itemId) {
        return cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .flatMap(cart -> cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElse(itemRepository.findById(itemId)
                                .map(CartItem::new)
                                .doOnNext(cartItem -> cart.getCartItems().add(cartItem))
                                .map(cartItem -> cart)
                        ))
                .flatMap(cartRepository::save)
                .thenReturn("redirect:/");
    }
}
