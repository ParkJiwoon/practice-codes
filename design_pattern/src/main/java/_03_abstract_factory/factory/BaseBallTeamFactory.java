package _03_abstract_factory.factory;

import _03_abstract_factory.model.BaseBallTeam;
import _03_abstract_factory.model.Team;

public class BaseBallTeamFactory extends TeamFactory {
    @Override
    protected Team createTeam() {
        return new BaseBallTeam();
    }
}
