package com.example.springjpa;

import com.example.springjpa.many_to_one.Member;
import com.example.springjpa.many_to_one.MemberRepository;
import com.example.springjpa.many_to_one.one_way.Car;
import com.example.springjpa.many_to_one.one_way.CarRepository;
import com.example.springjpa.many_to_one.two_way.Dog;
import com.example.springjpa.many_to_one.two_way.DogRepository;
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
    private MemberRepository memberRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DogRepository dogRepository;

    @DisplayName("다대일 단방향 매핑 Car 테스트")
    @Test
    void testCar() {
        // given
        Member member = new Member();
        member.setName("Alice");

        Car car = new Car();
        car.setMember(member);

        // when
        carRepository.save(car);

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(1);
        assertThat(carRepository.findAll().size()).isEqualTo(1);
    }

    @DisplayName("다대일 양방향 매핑 Dog 테스트")
    @Test
    void testDog() {
        // given
        Member member = new Member();
        member.setName("Alice");

        Dog dog = new Dog();
        dog.setName("Poppy");
        dog.setMember(member);
        member.getDogs().add(dog);

        // when
        dogRepository.save(dog);

        // then
        List<Member> members = memberRepository.findAll();
        List<Dog> dogs = dogRepository.findAll();

        assertThat(members.size()).isEqualTo(1);
        assertThat(dogs.size()).isEqualTo(1);
        assertThat(members.get(0).getDogs().size()).isEqualTo(1);
        assertThat(members.get(0).getDogs().get(0).getName()).isEqualTo("Poppy");
    }

    @DisplayName("양방향 매핑에서 연관관계 주인이 아니면 데이터가 제대로 저장되지 않음")
    @Test
    void testMappbedByMaster() {
        // given
        Member member = new Member();
        member.setName("Alice");

        Dog dog1 = new Dog();
        dog1.setName("Poppy");

        Dog dog2 = new Dog();
        dog2.setName("Kitty");

        // when
        member.getDogs().add(dog1);
        member.getDogs().add(dog2);
        memberRepository.save(member);
        dogRepository.save(dog1);
        dogRepository.save(dog2);

        // then
        List<Member> members = memberRepository.findAll();
        List<Dog> dogs = dogRepository.findAll();

        assertThat(members.size()).isEqualTo(1);
        assertThat(dogs.size()).isEqualTo(2);

        // Member 가 연관관계 주인이 아니기 때문에 데이터는 들어갔지만 dog.member_id 는 null 로 세팅되어 잇음
        assertThat(dogs.get(0).getMember()).isNull();
        assertThat(dogs.get(1).getMember()).isNull();
    }
}
