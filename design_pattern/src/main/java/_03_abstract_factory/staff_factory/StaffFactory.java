package _03_abstract_factory.staff_factory;

import _03_abstract_factory.staff_model.Player;
import _03_abstract_factory.staff_model.Manager;

public interface StaffFactory {
    Player createPlayer();
    Manager createManager();
}
