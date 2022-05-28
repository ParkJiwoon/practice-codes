package _01_factory_method.simple_factory;

import _01_factory_method.simple_factory.factory.PetFactory;
import _01_factory_method.simple_factory.model.Pet;

public class SimpleFactoryApp {
    public static void main(String[] args) {
        PetFactory petFactory = new PetFactory();

        Pet cat = petFactory.createPet(Pet.Type.CAT);
        cat.walk();

        Pet dog = petFactory.createPet(Pet.Type.DOG);
        dog.walk();
    }
}
