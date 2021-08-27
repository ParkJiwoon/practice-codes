package com.example.springjpa;

import com.example.springjpa.many_to_one.School;
import com.example.springjpa.many_to_one.SchoolRepository;
import com.example.springjpa.many_to_one.one_way.Student;
import com.example.springjpa.many_to_one.one_way.StudentRepository;
import com.example.springjpa.one_to_one.Person;
import com.example.springjpa.one_to_one.PersonRepository;
import com.example.springjpa.one_to_one.one_way.Company;
import com.example.springjpa.one_to_one.one_way.CompanyRepository;
import com.example.springjpa.one_to_one.two_way.House;
import com.example.springjpa.one_to_one.two_way.HouseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("연관관계에 따른 삭제 테스트")
@SpringBootTest
@Transactional
public class DeleteTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SchoolRepository schoolRepository;


    /**
     * Company 에서 CascadeType 을 PERSIST 로 설정하면 삭제할 때는 Person 값이 삭제되지 않음
     * ALL 로 설정하면 Company 를 삭제할 때 외래키로 연결된 Person 도 삭제됨
     * 회사가 없어진다고 사람이 사라지는 건 아니므로 스펙에 맞게 잘 설정해야함
     */
    @Autowired
    private CompanyRepository companyRepository;

    @DisplayName("일대일 매핑에서 CascadeType.REMOVE 설정하면 Company 를 삭제하면 Person 도 삭제")
    @Test
    void testOneToOneOneWay() {
        // given
        Person person = new Person();
        person.setName("Woody");

        Company company = new Company();
        company.setName("MyCompany");
        company.setPerson(person);

        // save data
        companyRepository.save(company);

        assertThat(companyRepository.findAll()).isNotEmpty();
        assertThat(personRepository.findAll()).isNotEmpty();

        // when
        List<Company> companies = companyRepository.findAll();
        companyRepository.delete(companies.get(0));

        // then
        assertThat(companyRepository.findAll()).isEmpty();
        assertThat(personRepository.findAll()).isEmpty();
    }


    @Autowired
    private HouseRepository houseRepository;

    @DisplayName("일대일 양방향 매핑에서 cascade 설정하면 연관관계 주인이 아니어도 같이 삭제")
    @Test
    void testOneToOneTwoWay() {
        // given
        Person person = new Person();
        person.setName("Woody");

        House house = new House();
        house.setAddress("Seoul");
        house.setPerson(person);
        person.setHouse(house);

        // save data
        personRepository.save(person);

        assertThat(houseRepository.findAll()).isNotEmpty();
        assertThat(personRepository.findAll()).isNotEmpty();

        // when
        List<Person> persons = personRepository.findAll();
        personRepository.delete(persons.get(0));

        // then
        assertThat(houseRepository.findAll()).isEmpty();
        assertThat(personRepository.findAll()).isEmpty();
    }


    @Autowired
    private StudentRepository studentRepository;

    @DisplayName("다대일 단방향 매핑에서 다(N)쪽이 하나뿐인데 삭제하면 일(1)쪽도 삭제됨")
    @Test
    void testManyToOneOneWay() {
        // given
        School school = new School();
        Student student = new Student();
        student.setName("Kenny");
        student.setSchool(school);

        // save data
        studentRepository.save(student);

        assertThat(studentRepository.findAll()).isNotEmpty();
        assertThat(schoolRepository.findAll()).isNotEmpty();

        // when
        List<Student> students = studentRepository.findAll();
        studentRepository.delete(students.get(0));

        // then
        assertThat(studentRepository.findAll()).isEmpty();
        assertThat(schoolRepository.findAll()).isEmpty();
    }

    @DisplayName("다대일 단방향 매핑에서 다(N) 쪽이 두개 이상이면 삭제해도 일(1) 쪽은 남아있음")
    @Test
    void testManyToOneOneWayTwoElements() {
        // given
        School school = new School();
        Student student1 = new Student();
        student1.setName("Kenny");
        student1.setSchool(school);

        Student student2 = new Student();
        student2.setName("Neo");
        student2.setSchool(school);

        // save data
        studentRepository.save(student1);
        studentRepository.save(student2);

        assertThat(studentRepository.findAll()).isNotEmpty();
        assertThat(schoolRepository.findAll()).isNotEmpty();

        // when
        List<Student> students = studentRepository.findAll();
        studentRepository.delete(students.get(0));

        // then
        assertThat(studentRepository.findAll()).isNotEmpty();
        assertThat(schoolRepository.findAll()).isNotEmpty();
    }
}
