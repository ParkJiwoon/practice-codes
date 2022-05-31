package _02_abstract_factory.factory;

import _02_abstract_factory.model.Player;
import _02_abstract_factory.model.Manager;

public interface StaffFactory {
    Manager createManager();
    Player createPlayer();
}
