package factory_method._02_simple_factory.factory;

import factory_method._02_simple_factory.model.Cat;
import factory_method._02_simple_factory.model.Dog;
import factory_method._02_simple_factory.model.Pet;

public class SimplePetFactory {

    public Pet createPet(String petType) {
        System.out.println("Pet 생성하기");

        switch (petType) {
            case "Cat":
                return new Cat();
            case "Dog":
                return new Dog();
            default:
                throw new IllegalArgumentException("Pet 타입이 아닙니다");
        }
    }
}
