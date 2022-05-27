package _01_simple_factory.model;

public class Dog implements Pet {

    public Dog() {
        System.out.println("새로운 강아지 생성");
    }

    @Override
    public void walk() {
        System.out.println("강아지랑 산책하다");
    }
}
