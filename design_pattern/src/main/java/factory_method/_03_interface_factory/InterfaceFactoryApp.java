package factory_method._03_interface_factory;

import factory_method.model.Pet;

public class InterfaceFactoryApp {
    public static void main(String[] args) {
        doWalk(new ConcreteCatFactory());
        doWalk(new ConcreteDogFactory());
    }

    /**
     * Interface 를 파라미터로 받아 일종의 DI 를 구현할 수 있음
     */
    private static void doWalk(PetFactory petFactory) {
        Pet pet = petFactory.createPet();
        pet.walk();
    }
}
