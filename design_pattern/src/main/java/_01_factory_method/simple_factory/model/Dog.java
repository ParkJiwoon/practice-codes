package _01_factory_method.simple_factory.model;

public class Dog implements Pet {
    @Override
    public void walk() {
        System.out.println("강아지랑 산책하다");
    }
}
