package _03_abstract_factory.model;

import _03_abstract_factory.staff_model.Player;
import _03_abstract_factory.staff_model.Manager;

public interface Team {
    void setPlayer(Player player);
    void setManager(Manager manager);
}
