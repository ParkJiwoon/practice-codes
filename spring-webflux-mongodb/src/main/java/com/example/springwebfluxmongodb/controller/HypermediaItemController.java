package com.example.springwebfluxmongodb.controller;

import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class HypermediaItemController {

    private final ItemRepository itemRepository;

    public HypermediaItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/hypermedia/items")
    Mono<CollectionModel<EntityModel<Item>>> findAll() {
        return itemRepository.findAll() //
                .flatMap(item -> findOne(item.getId()))
                .collectList()
                .flatMap(entityModels -> linkTo(methodOn(HypermediaItemController.class)
                        .findAll()).withSelfRel()
                        .toMono()
                        .map(selfLink -> CollectionModel.of(entityModels, selfLink)));
    }

    @GetMapping("/hypermedia/items/{id}")
    public Mono<EntityModel<Item>> findOne(@PathVariable String id) {
        // 컨트롤러에 대한 프록시 생성
        HypermediaItemController controller = methodOn(HypermediaItemController.class);

        // 컨트롤러의 findOne() 메소드에 대한 링크 생성
        Mono<Link> selfLink = linkTo(controller.findOne(id)).withSelfRel().toMono();

        // 컨트롤러의 findAll() 메소드에 대한 링크 생성
        Mono<Link> aggregateLink = linkTo(controller.findAll()).withRel(IanaLinkRelations.ITEM).toMono();

        // 여러 개의 결과를 하나로 합치는 Mono.zip() 메소드 사용
        return Mono.zip(itemRepository.findById(id), selfLink, aggregateLink)
                .map(o -> EntityModel.of(o.getT1(), Links.of(o.getT2(), o.getT3())));
    }
}
