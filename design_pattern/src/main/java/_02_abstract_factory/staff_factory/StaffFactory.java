package _02_abstract_factory.staff_factory;

import _02_abstract_factory.staff_model.Player;
import _02_abstract_factory.staff_model.Manager;

public interface StaffFactory {
    Player createPlayer();
    Manager createManager();
}
