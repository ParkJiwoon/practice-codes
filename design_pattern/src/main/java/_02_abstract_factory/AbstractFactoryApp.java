package _02_abstract_factory;

import _02_abstract_factory.factory.BaseBallTeamFactory;
import _02_abstract_factory.factory.SoccerTeamFactory;
import _02_abstract_factory.factory.TeamFactory;
import _02_abstract_factory.model.Team;
import _02_abstract_factory.staff_factory.BaseBallStaffFactory;
import _02_abstract_factory.staff_factory.SoccerStaffFactory;

public class AbstractFactoryApp {
    public static void main(String[] args) {
        TeamFactory soccerTeamFactory = new SoccerTeamFactory();
        Team soccerTeam = soccerTeamFactory.newInstance(new SoccerStaffFactory());

        TeamFactory baseBallTeamFactory = new BaseBallTeamFactory();
        Team baseBallTeam = baseBallTeamFactory.newInstance(new BaseBallStaffFactory());
    }
}
