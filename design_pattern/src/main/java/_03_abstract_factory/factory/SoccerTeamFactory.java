package _03_abstract_factory.factory;

import _03_abstract_factory.model.Team;
import _03_abstract_factory.model.SoccerTeam;

public class SoccerTeamFactory extends TeamFactory {

    @Override
    protected Team createTeam() {
        return new SoccerTeam();
    }
}
