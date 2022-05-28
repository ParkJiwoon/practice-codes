package _03_abstract_factory;

import _03_abstract_factory.factory.BaseBallTeamFactory;
import _03_abstract_factory.factory.SoccerTeamFactory;
import _03_abstract_factory.factory.TeamFactory;
import _03_abstract_factory.model.Team;
import _03_abstract_factory.staff_factory.BaseBallStaffFactory;
import _03_abstract_factory.staff_factory.SoccerStaffFactory;

public class AbstractFactoryApp {
    public static void main(String[] args) {
        TeamFactory soccerTeamFactory = new SoccerTeamFactory();
        Team soccerTeam = soccerTeamFactory.newInstance(new SoccerStaffFactory());

        TeamFactory baseBallTeamFactory = new BaseBallTeamFactory();
        Team baseBallTeam = baseBallTeamFactory.newInstance(new BaseBallStaffFactory());
    }
}
