package com.example.springjpa.oneway.onetoone;

import com.example.springjpa.oneway.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @OneToOne
    private Person person;

    public House(String address, Person person) {
        this.address = address;
        this.person = person;
    }
}
