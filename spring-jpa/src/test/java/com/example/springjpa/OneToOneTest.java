package com.example.springjpa;

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

@DisplayName("일대일 매핑 테스트")
@SpringBootTest
@Transactional
public class OneToOneTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private PersonRepository personRepository;

    @DisplayName("일대일 단방향 매핑 Company 테스트")
    @Test
    void testCompany() {
        // given
        Person person = new Person();
        person.setName("Alice");

        Company company = new Company();
        company.setName("MyCompany");
        company.setPerson(person);

        // when
        companyRepository.save(company);

        // then
        assertThat(companyRepository.findAll()).isNotEmpty();
        assertThat(personRepository.findAll()).isNotEmpty();
    }

    @DisplayName("일대일 양방향 매핑 House 테스트")
    @Test
    void testHouse() {
        // given
        Person person = new Person();
        person.setName("Alice");

        House house = new House();
        house.setAddress("Seoul");
        house.setPerson(person);

        // when
        houseRepository.save(house);

        // then
        assertThat(houseRepository.findAll()).isNotEmpty();
        assertThat(personRepository.findAll()).isNotEmpty();
    }

    @DisplayName("House 는 연관 관계의 주인이기 때문에 외래키 person_id 변경 가능")
    @Test
    void testMappedByMaster() {
        // given
        Person person = new Person();
        person.setName("Alice");

        House house = new House();
        house.setAddress("Seoul");
        house.setPerson(person);

        // findAll 호출해서 강제로 쿼리 flush
        houseRepository.save(house);
        forceFlush();

        // House 객체에서 Person 변경
        Person newPerson = new Person();
        newPerson.setName("Bob");
        house.setPerson(newPerson);
        forceFlush();

        // Alice, Bob 두 개의 값이 들어감
        List<Person> persons = personRepository.findAll();
        assertThat(persons.size()).isEqualTo(2);

        // House 의 person_id 외래키가 변경되었음
        List<House> houses = houseRepository.findAll();
        assertThat(houses.size()).isEqualTo(1);
        assertThat(houses.get(0).getPerson().getName()).isEqualTo("Bob");
    }

    @DisplayName("Person 은 연관 관계의 주인이 아니라 house.person_id 변경 불가능")
    @Test
    void testMappedBySlave() {
        // given
        Person person = new Person();
        person.setName("Alice");

        House house = new House();
        house.setAddress("Seoul");
        house.setPerson(person);

        // findAll 호출해서 강제로 쿼리 flush
        houseRepository.save(house);
        houseRepository.findAll();

        // Person 객체에서 House null 세팅
        List<Person> persons = personRepository.findAll();
        persons.get(0).setHouse(null);
        persons.get(0).setName("Bob");
        personRepository.save(persons.get(0));
        forceFlush();

        // house 를 Null 로 세팅했었지만 변경되지 않음
        List<House> houses = houseRepository.findAll();
        assertThat(houses.size()).isEqualTo(1);
        assertThat(houses.get(0).getPerson().getName()).isEqualTo("Bob");
    }

    private void forceFlush() {
        companyRepository.findAll();
        houseRepository.findAll();
        personRepository.findAll();
    }
}
