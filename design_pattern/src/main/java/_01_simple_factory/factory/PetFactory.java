package _01_simple_factory.factory;

import _01_simple_factory.model.Cat;
import _01_simple_factory.model.Dog;
import _01_simple_factory.model.Pet;
import _01_simple_factory.model.PetType;

public class PetFactory {

    public Pet createPet(PetType petType) {
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
