package factory_method._03_interface_factory;

import factory_method.model.Pet;

interface PetFactory {

    default Pet createPet() {
        System.out.println("Pet 생성하기");
        return newPet();
    }

    Pet newPet();
}
