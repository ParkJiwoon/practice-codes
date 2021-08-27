package com.example.springjpa.many_to_one;

import com.example.springjpa.many_to_one.two_way.Teacher;
import com.example.springjpa.many_to_one.two_way.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class MyService {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;

    public void createByRelationMaster() {
        School school = new School();
        schoolRepository.save(school);

        Teacher teacher1 = new Teacher();
        teacher1.setName("Alice");
        teacher1.setSchool(school);

        Teacher teacher2 = new Teacher();
        teacher2.setName("Bob");
        teacher2.setSchool(school);

        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
    }

    public void createByRelationSlave() {
        School school = new School();

        Teacher teacher1 = new Teacher();
        teacher1.setName("Alice");

        Teacher teacher2 = new Teacher();
        teacher2.setName("Bob");

        school.getTeachers().add(teacher1);
        school.getTeachers().add(teacher2);
        schoolRepository.save(school);
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
    }

    public void deleteTeacherBySlave() {
        List<School> schools = schoolRepository.findAll();
        School school = schools.get(0);
        school.getTeachers().clear();
    }

    public List<String> findAllTeacherNamesBySchool() {
        List<School> schools = schoolRepository.findAll();
        return schools.get(0)
                      .getTeachers()
                      .stream()
                      .map(Teacher::getName)
                      .collect(Collectors.toList());
    }

    public List<String> findAllTeachers() {
        return teacherRepository.findAll()
                                .stream()
                                .map(Teacher::getName)
                                .collect(Collectors.toList());
    }

    public List<School> findSchoolsByTeacher() {
        return teacherRepository.findAll()
                                .stream()
                                .map(Teacher::getSchool)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList());
    }
}
