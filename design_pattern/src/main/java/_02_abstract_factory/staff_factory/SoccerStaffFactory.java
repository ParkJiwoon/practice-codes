package _02_abstract_factory.staff_factory;

import _02_abstract_factory.staff_model.SoccerPlayer;
import _02_abstract_factory.staff_model.SoccerManager;
import _02_abstract_factory.staff_model.Player;
import _02_abstract_factory.staff_model.Manager;

public class SoccerStaffFactory implements StaffFactory {
    @Override
    public Player createPlayer() {
        return new SoccerPlayer();
    }

    @Override
    public Manager createManager() {
        return new SoccerManager();
    }
}
