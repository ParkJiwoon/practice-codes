package _02_abstract_factory.staff_factory;

import _02_abstract_factory.staff_model.BaseBallManager;
import _02_abstract_factory.staff_model.BaseBallPlayer;
import _02_abstract_factory.staff_model.Manager;
import _02_abstract_factory.staff_model.Player;

public class BaseBallStaffFactory implements StaffFactory {
    @Override
    public Player createPlayer() {
        return new BaseBallPlayer();
    }

    @Override
    public Manager createManager() {
        return new BaseBallManager();
    }
}
