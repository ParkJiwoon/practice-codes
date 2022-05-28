package _03_abstract_factory.model;

import _03_abstract_factory.staff_model.Manager;
import _03_abstract_factory.staff_model.Player;

public class BaseBallTeam implements Team {
    @Override
    public void setPlayer(Player player) {
    }

    @Override
    public void setManager(Manager manager) {
    }
}
