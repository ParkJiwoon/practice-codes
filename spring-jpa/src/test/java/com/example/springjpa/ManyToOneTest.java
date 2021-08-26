package com.example.springjpa;

import com.example.springjpa.many_to_one.School;
import com.example.springjpa.many_to_one.SchoolRepository;
import com.example.springjpa.many_to_one.one_way.Student;
import com.example.springjpa.many_to_one.one_way.StudentRepository;
import com.example.springjpa.many_to_one.two_way.Teacher;
import com.example.springjpa.many_to_one.two_way.TeacherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("다대일 매핑 테스트")
@SpringBootTest
@Transactional
public class ManyToOneTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @DisplayName("다대일 단방향 매핑 Student 테스트")
    @Test
    void testStudent() {
        // given
        School school = new School();
        Student student = new Student();
        student.setName("Bob");
        student.setSchool(school);

        // when
        studentRepository.save(student);

        // then
        assertThat(schoolRepository.findAll().size()).isEqualTo(1);
        assertThat(studentRepository.findAll().size()).isEqualTo(1);
    }

    @DisplayName("다대일 양방향 매핑 Teacher 테스트")
    @Test
    void testTeacher() {
        // given
        School school = new School();
        Teacher teacher = new Teacher();
        teacher.setName("Poppy");
        teacher.setSchool(school);
        school.getTeachers().add(teacher);

        // when
        teacherRepository.save(teacher);

        // then
        List<School> schools = schoolRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();

        assertThat(schools.size()).isEqualTo(1);
        assertThat(teachers.size()).isEqualTo(1);
        assertThat(schools.get(0).getTeachers().size()).isEqualTo(1);
        assertThat(schools.get(0).getTeachers().get(0).getName()).isEqualTo("Poppy");
    }

    @DisplayName("양방향 매핑에서 연관관계 주인이 아니면 데이터가 제대로 저장되지 않음")
    @Test
    void testMappedByMaster() {
        // given
        School school = new School();

        Teacher teacher1 = new Teacher();
        teacher1.setName("Poppy");

        Teacher teacher2 = new Teacher();
        teacher2.setName("Kitty");

        // when
        school.getTeachers().add(teacher1);
        school.getTeachers().add(teacher2);
        schoolRepository.save(school);
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);

        // then
        List<School> schools = schoolRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();

        assertThat(schools.size()).isEqualTo(1);
        assertThat(teachers.size()).isEqualTo(2);

        // School 이 연관관계 주인이 아니기 때문에 데이터는 들어갔지만 teacher.school_id 는 null 로 세팅되어 잇음
        assertThat(teachers.get(0).getSchool()).isNull();
        assertThat(teachers.get(1).getSchool()).isNull();
    }
}
