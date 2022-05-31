package _02_abstract_factory.factory;

import _02_abstract_factory.model.SoccerPlayer;
import _02_abstract_factory.model.SoccerManager;
import _02_abstract_factory.model.Player;
import _02_abstract_factory.model.Manager;

public class SoccerStaffFactory implements StaffFactory {

    @Override
    public Manager createManager() {
        return new SoccerManager();
    }

    @Override
    public Player createPlayer() {
        return new SoccerPlayer();
    }
}
