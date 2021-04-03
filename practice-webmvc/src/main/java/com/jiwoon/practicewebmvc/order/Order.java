package com.jiwoon.practicewebmvc.order;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Order {
    Long memberId;
    String itemName;
    Integer itemPrice;
    Integer discountPrice;

    public Order(Long memberId, String itemName, Integer itemPrice, Integer discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }
}
