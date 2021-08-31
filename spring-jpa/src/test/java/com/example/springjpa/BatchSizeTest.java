package com.example.springjpa;

import com.example.springjpa.batch_size.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DisplayName("JPA 배치 사이즈 테스트")
@SpringBootTest
public class BatchSizeTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private FamilyService familyService;

    @DisplayName("Parent 로 Child 배치 사이즈 호출")
    @Transactional
    @Test
    void testParent() {
        familyService.saveFamily();

        List<Parent> parents = parentRepository.findAll();

        // 실제로 사용해야 쿼리가 나가기 때문에 size() 까지 호출해줌
        parents.get(0).getChildren().size();
        parents.get(1).getChildren().size();
    }

    @DisplayName("Child 로 Parent 배치 사이즈 호출")
    @Transactional
    @Test
    void testChild() {
        familyService.saveFamily();

        List<Child> children = childRepository.findAll();

        // 실제로 사용해야 쿼리가 나가기 때문에 getter 까지 호출해줌
        children.get(0).getParent().getName();
        children.get(1).getParent().getName();
        children.get(2).getParent().getName();
    }
}
