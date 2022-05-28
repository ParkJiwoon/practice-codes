package _01_factory_method.simple_factory.factory;

import _01_factory_method.simple_factory.model.Cat;
import _01_factory_method.simple_factory.model.Dog;
import _01_factory_method.simple_factory.model.Pet;

public class PetFactory {

    public Pet createPet(Pet.Type petType) {
        switch (petType) {
            case CAT:
                return new Cat();
            case DOG:
                return new Dog();
            default:
                throw new IllegalArgumentException("Pet 타입이 아닙니다");
        }
    }
}
