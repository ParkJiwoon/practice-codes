package com.example.springjpa.batch_size;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class FamilyService {

    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;

    public void saveFamily() {
        // 부모 1 명과 아이 2 명 저장
        Parent parent1 = new Parent();
        parent1.setName("parent 1");

        Child child1 = new Child();
        child1.setName("child 1");
        child1.setParent(parent1);
        parent1.getChildren().add(child1);

        Child child2 = new Child();
        child2.setName("child 2");
        child2.setParent(parent1);
        parent1.getChildren().add(child2);

        // 부모 1명 아이 1명 저장
        Parent parent2 = new Parent();
        parent2.setName("parent 2");

        Child child3 = new Child();
        child3.setName("child 3");
        child3.setParent(parent2);
        parent2.getChildren().add(child3);

        parentRepository.save(parent1);
        parentRepository.save(parent2);
        childRepository.save(child1);
        childRepository.save(child2);
        childRepository.save(child3);
    }
}
