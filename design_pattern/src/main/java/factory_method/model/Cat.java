package factory_method.model;

public class Cat implements Pet {

    public Cat() {
        System.out.println("새로운 고양이 생성");
    }

    @Override
    public void walk() {
        System.out.println("고양이랑 산책하다");
    }
}
