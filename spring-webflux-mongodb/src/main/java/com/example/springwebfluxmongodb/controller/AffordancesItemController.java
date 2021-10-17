package com.example.springwebfluxmongodb.controller;

import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class AffordancesItemController {

    private final ItemRepository itemRepository;

    public AffordancesItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/affordances")
    Mono<RepresentationModel<?>> root() {
        AffordancesItemController controller = methodOn(AffordancesItemController.class);

        Mono<Link> selfLink = linkTo(controller.root())
                .withSelfRel()
                .toMono();

        Mono<Link> itemsAggregateLink = linkTo(controller.findAll())
                .withRel(IanaLinkRelations.ITEM)
                .toMono();

        return selfLink.zipWith(itemsAggregateLink)
                .map(links -> Links.of(links.getT1(), links.getT2()))
                .map(links -> new RepresentationModel<>(links.toList()));
    }

    @GetMapping("/affordances/items")
    Mono<CollectionModel<EntityModel<Item>>> findAll() {
        AffordancesItemController controller = methodOn(AffordancesItemController.class);

        Mono<Link> aggregateRoot = linkTo(controller.findAll())
                .withSelfRel()
                .andAffordance(controller.addNewItem(null))
                .toMono();

        return itemRepository.findAll()
                .flatMap(item -> findOne(item.getId()))
                .collectList()
                .flatMap(models -> aggregateRoot
                        .map(selfLink -> CollectionModel.of(models, selfLink)));
    }

    @GetMapping("/affordances/items/{id}")
    public Mono<EntityModel<Item>> findOne(@PathVariable String id) {
        AffordancesItemController controller = methodOn(AffordancesItemController.class);

        Mono<Link> selfLink = linkTo(controller.findOne(id))
                .withSelfRel()
                .andAffordance(controller.updateItem(null, id))
                .toMono();

        Mono<Link> aggregateLink = linkTo(controller.findAll())
                .withRel(IanaLinkRelations.ITEM)
                .toMono();

        return Mono.zip(itemRepository.findById(id), selfLink, aggregateLink)
                .map(o -> EntityModel.of(o.getT1(), Links.of(o.getT2(), o.getT3())));
    }

    @PostMapping("/affordances/items")
    Mono<ResponseEntity<?>> addNewItem(@RequestBody Mono<EntityModel<Item>> item) {
        return item.map(EntityModel::getContent)
                .flatMap(itemRepository::save)
                .map(Item::getId)
                .flatMap(this::findOne)
                .map(newModel -> ResponseEntity.created(newModel
                                            .getRequiredLink(IanaLinkRelations.SELF)
                                            .toUri()).body(newModel.getContent()));
    }

    @PutMapping("/affordances/items/{id}")
    public Mono<ResponseEntity<?>> updateItem(
            @RequestBody Mono<EntityModel<Item>> item,  // EntityModel 은 클라이언트가 Item 객체를 보낼 수도 있고 하이퍼미디어 형식일 수도 의미함
            @PathVariable String id
    ) {
        return item.map(EntityModel::getContent)
                .map(content -> new Item(id, content.getName(), content.getDescription(), content.getPrice()))
                .flatMap(itemRepository::save)
                .then(findOne(id))
                .map(model -> ResponseEntity.noContent()
                                            .location(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).build());
    }
}
