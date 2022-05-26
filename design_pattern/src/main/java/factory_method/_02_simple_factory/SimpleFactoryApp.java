package factory_method._02_simple_factory;

import factory_method._02_simple_factory.factory.SimplePetFactory;
import factory_method._02_simple_factory.model.Pet;

public class SimpleFactoryApp {
    public static void main(String[] args) {
        SimplePetFactory petFactory = new SimplePetFactory();

        Pet cat = petFactory.createPet("Cat");
        Pet dog = petFactory.createPet("Dog");

        cat.walk();
        dog.walk();
    }
}
