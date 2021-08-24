package com.example.springjpa.oneway.manytoone;

import com.example.springjpa.oneway.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Person person;

    public Car(String name, Person person) {
        this.name = name;
        this.person = person;
    }
}
