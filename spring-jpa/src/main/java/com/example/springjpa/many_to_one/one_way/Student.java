package com.example.springjpa.many_to_one.one_way;

import com.example.springjpa.many_to_one.School;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Student(N) -> School(1)
 *
 *     create table student (
 *        student_id bigint generated by default as identity,
 *         name varchar(255),
 *         school_id bigint,
 *         primary key (student_id)
 *     )
 */

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private School school;
}