package com.example.springjpa.batch_size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Child> children = new ArrayList<>();
}
