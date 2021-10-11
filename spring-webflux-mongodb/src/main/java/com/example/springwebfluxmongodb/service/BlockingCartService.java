package com.example.springwebfluxmongodb.service;

import com.example.springwebfluxmongodb.entity.Cart;
import com.example.springwebfluxmongodb.entity.CartItem;
import com.example.springwebfluxmongodb.repository.CartRepository;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BlockingCartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public BlockingCartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<Cart> addItemToCartBlocking(String cartId, String itemId) {
        // 블로킹 코드 호출
        Cart myCart = cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .block();

        return myCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
                .findAny()
                .map(cartItem -> {
                    cartItem.increment();
                    return Mono.just(myCart);
                })
                .orElseGet(() -> itemRepository.findById(itemId)
                        .map(item -> new CartItem(item))
                        .map(cartItem -> {
                            myCart.getCartItems().add(cartItem);
                            return myCart;
                        }))
                .flatMap(cartRepository::save);
    }
}
