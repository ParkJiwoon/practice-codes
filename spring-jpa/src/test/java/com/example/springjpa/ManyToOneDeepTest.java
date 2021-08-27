package com.example.springjpa;

import com.example.springjpa.many_to_one.MyService;
import com.example.springjpa.many_to_one.School;
import com.example.springjpa.many_to_one.SchoolRepository;
import com.example.springjpa.many_to_one.two_way.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("다대일 양방향 연관관계 심화 테스트")
@SpringBootTest
public class ManyToOneDeepTest {

    @Autowired
    private MyService myService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @BeforeEach
    void beforeEach() {
        teacherRepository.deleteAll();
        schoolRepository.deleteAll();
    }

    @DisplayName("연관관계의 주인쪽만 저장하면 데이터가 저장됨. " +
            "그리고 다른 트랜잭션이면 getter 로 Collection 을 가져올 수 있음")
    @Test
    void testCreateByRelationMaster() {
        myService.createByRelationMaster();
        List<String> teacherNames = myService.findAllTeacherNamesBySchool();
        assertThat(teacherNames).containsExactly("Alice", "Bob");
    }

    @DisplayName("연관 관계의 주인이 아니면 외래키 저장 불가능")
    @Test
    void testCreateByRelationSlave() {
        myService.createByRelationSlave();

        // School 데이터는 있지만 객체 그래프 탐색으로 Teacher 를 가져올 수 없음
        List<String> teacherNames1 = myService.findAllTeacherNamesBySchool();
        assertThat(teacherNames1).isEmpty();

        // Teacher 에 데이터는 들어감
        List<String> teacherNames2 = myService.findAllTeachers();
        assertThat(teacherNames2).containsExactly("Alice", "Bob");

        // 외래키인 teacher.school_id 가 null 로 되어 있음
        List<School> schools = myService.findSchoolsByTeacher();
        assertThat(schools).isEmpty();
    }

    @DisplayName("연관 관계의 주인이 아닌 쪽 (Colletions) 에서 지운게 반영되지 않음" +
                 "@OneToMany 에 orphanRemovel=true 옵션을 주면 지워짐")
    @Test
    void testDeleteBySlave() {
        myService.createByRelationMaster(); // school 1개, Alice, Bob 저장되었음.
        myService.deleteTeacherBySlave();

        List<String> teacherNames = myService.findAllTeachers();
        assertThat(teacherNames).containsExactly("Alice", "Bob");
    }

    @DisplayName("연관 관계의 주인이 아닌 쪽을 지워도 cascade 옵션 때문에 연관된 엔티티가 전부 지워짐")
    @Test
    void testCascadeRemove() {
        myService.createByRelationMaster(); // school 1개, Alice, Bob 저장되었음.
        schoolRepository.deleteAll();

        assertThat(teacherRepository.findAll()).isEmpty();
    }
}
