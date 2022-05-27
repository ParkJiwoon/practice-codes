package _01_simple_factory;

import _01_simple_factory.factory.PetFactory;
import _01_simple_factory.model.Pet;
import _01_simple_factory.model.PetType;

public class SimpleFactoryApp {
    public static void main(String[] args) {
        PetFactory petFactory = new PetFactory();

        Pet cat = petFactory.createPet(PetType.CAT);
        cat.walk();

        Pet dog = petFactory.createPet(PetType.DOG);
        dog.walk();
    }
}
