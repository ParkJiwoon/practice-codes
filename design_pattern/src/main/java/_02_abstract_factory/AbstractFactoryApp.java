package _02_abstract_factory;

import _02_abstract_factory.factory.TennisStaffFactory;
import _02_abstract_factory.factory.SoccerStaffFactory;
import _02_abstract_factory.factory.StaffFactory;
import _02_abstract_factory.model.Manager;
import _02_abstract_factory.model.Player;

public class AbstractFactoryApp {
    public static void main(String[] args) {
        use(new SoccerStaffFactory());
        use(new TennisStaffFactory());
    }

    private static void use(StaffFactory factory) {
        Manager manager = factory.createManager();
        Player player = factory.createPlayer();
    }
}
