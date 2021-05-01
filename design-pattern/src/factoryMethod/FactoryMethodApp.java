package factoryMethod;

public class FactoryMethodApp {
    public static void main(String[] args) {
//        Pet dog = new Dog();
//        Pet cat = new Cat();

        PetFactory petFactory = new PetFactory();

        Pet dog = petFactory.createPet(PetType.DOG);
        Pet cat = petFactory.createPet(PetType.CAT);

        dog.walk();
        cat.walk();
    }
}
