package com.example.springjpa.one_to_one;

import com.example.springjpa.one_to_one.two_way.House;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 *     create table person (
 *        person_id bigint generated by default as identity,
 *         name varchar(255),
 *         primary key (person_id)
 *     )
 */

@Getter
@Setter
@Entity
@ToString(exclude = "house")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private House house;
}