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

    public Mono<Cart> addItemToCart(String cartId, String itemId) {
        return cartRepository.findById(cartId)
                .log("foundCart")
                .defaultIfEmpty(new Cart(cartId))
                .log("emptyCart")
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
                .log("cartWithAnotherItem")
                .flatMap(cartRepository::save)
                .log("saveCart");
    }
}
