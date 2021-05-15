package com.practice.transactional;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Getter
@ToString
@NoArgsConstructor
public class Member {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
