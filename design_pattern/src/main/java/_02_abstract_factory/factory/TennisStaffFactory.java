package _02_abstract_factory.factory;

import _02_abstract_factory.model.TennisManager;
import _02_abstract_factory.model.TennisPlayer;
import _02_abstract_factory.model.Manager;
import _02_abstract_factory.model.Player;

public class TennisStaffFactory implements StaffFactory {

    @Override
    public Manager createManager() {
        return new TennisManager();
    }

    @Override
    public Player createPlayer() {
        return new TennisPlayer();
    }
}
