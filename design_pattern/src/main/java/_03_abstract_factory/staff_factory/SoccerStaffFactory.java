package _03_abstract_factory.staff_factory;

import _03_abstract_factory.staff_model.SoccerPlayer;
import _03_abstract_factory.staff_model.SoccerManager;
import _03_abstract_factory.staff_model.Player;
import _03_abstract_factory.staff_model.Manager;

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
