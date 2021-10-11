package com.example.springwebfluxmongodb.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemUnitTest {

    @DisplayName("Item 객체 테스트 (constructor, getter, toString, equals)")
    @Test
    void itemBasicsShouldWork() {
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);

        assertThat(sampleItem.getId()).isEqualTo("item1");
        assertThat(sampleItem.getName()).isEqualTo("TV tray");
        assertThat(sampleItem.getDescription()).isEqualTo("Alf TV tray");
        assertThat(sampleItem.getPrice()).isEqualTo(19.99);

        assertThat(sampleItem.toString()).isEqualTo("Item{id='item1', name='TV tray', description='Alf TV tray', price=19.99}");

        Item sampleItem2 = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        assertThat(sampleItem).isEqualTo(sampleItem2);
    }
}
