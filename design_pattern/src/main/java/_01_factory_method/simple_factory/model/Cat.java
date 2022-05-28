package _01_factory_method.simple_factory.model;

public class Cat implements Pet {
    @Override
    public void walk() {
        System.out.println("고양이랑 산책하다");
    }
}
