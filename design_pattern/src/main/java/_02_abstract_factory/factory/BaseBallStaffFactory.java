package _02_abstract_factory.factory;

import _02_abstract_factory.model.BaseBallManager;
import _02_abstract_factory.model.BaseBallPlayer;
import _02_abstract_factory.model.Manager;
import _02_abstract_factory.model.Player;

public class BaseBallStaffFactory implements StaffFactory {

    @Override
    public Manager createManager() {
        return new BaseBallManager();
    }

    @Override
    public Player createPlayer() {
        return new BaseBallPlayer();
    }
}
