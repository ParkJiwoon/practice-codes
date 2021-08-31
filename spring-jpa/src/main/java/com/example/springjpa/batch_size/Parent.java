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
//@BatchSize(size = 100)    // 다른 엔티티에서 Parent 프록시를 호출할 때 배치사이즈 적용
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long id;

    private String name;

    // @BatchSize(size = 100) getChildren() 호출할 때 배치 사이즈 적용
    @OneToMany(mappedBy = "parent")
    private List<Child> children = new ArrayList<>();
}
