package com.example.springjpa;

import com.example.springjpa.oneway.manytoone.Car;
import com.example.springjpa.oneway.manytoone.CarRepository;
import com.example.springjpa.oneway.onetoone.House;
import com.example.springjpa.oneway.onetoone.HouseRepository;
import com.example.springjpa.oneway.Person;
import com.example.springjpa.oneway.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**.
 *
 * 요구사항
 * - 사람 한명 (Person) 당 집 (House) 하나씩 갖고 있음 (1:1)
 * - 사람 한명 (Person) 당 차 (Car) 를 여러개 갖고 있음 (1:N)
 *
 * 한쪽에서만 참조하는 단방향 관계
 *
 * Hibernate 가 자동으로 생성한 테이블
 *
 *     create table person (
 *         id bigint not null,
 *         name varchar(255),
 *         primary key (id)
 *     )
 *
 *     create table house (
 *         id bigint not null,
 *         address varchar(255),
 *         person_id bigint,
 *         primary key (id)
 *     )
 *
 *     create table car (
 *         id bigint not null,
 *         name varchar(255),
 *         person_id bigint,
 *         primary key (car_id)
 *     )
 */

@DisplayName("단방향 테스트")
@SpringBootTest
@Transactional
public class OneWayTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private CarRepository carRepository;

    @DisplayName("일대일 관계일 때 Person 만 save")
    @Test
    void savePersonWhenOneToOne() {
        Person person = new Person("Kim Chul Soo 1");
        House house = new House("Seoul 1", person);

        personRepository.save(person);

        System.out.println(personRepository.findAll());
        System.out.println(houseRepository.findAll());
    }

    @DisplayName("일대일 관계일 때 House 만 save")
    @Test
    void saveHouseWhenOneToOne() {
        Person person = new Person("Kim Chul Soo 2");
        House house = new House("Seoul 2", person);

        houseRepository.save(house);

        System.out.println(personRepository.findAll());
        System.out.println(houseRepository.findAll());
    }

    @DisplayName("다대일 관계일 때 일 (Person) 쪽만 save")
    @Test
    void saveOneWhenOneToMany() {
        Person person = new Person("Kim Chul Soo 3");
        Car car = new Car("MyCar 1", person);

        personRepository.save(person);

        System.out.println(personRepository.findAll());
        System.out.println(carRepository.findAll());
    }

    @DisplayName("다대일 관계일 때 다 (Car) 쪽만 save")
    @Test
    void saveManyWhenOneToMany() {
        Person person = new Person("Kim Chul Soo 4");
        Car car = new Car("MyCar 2", person);

        carRepository.save(car);

        System.out.println(personRepository.findAll());
        System.out.println(carRepository.findAll());
    }
}
