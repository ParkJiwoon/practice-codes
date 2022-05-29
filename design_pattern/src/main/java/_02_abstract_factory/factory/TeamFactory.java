package _02_abstract_factory.factory;

import _02_abstract_factory.model.*;
import _02_abstract_factory.staff_factory.StaffFactory;

public abstract class TeamFactory {

    public Team newInstance(StaffFactory staffFactory) {
        Team team = createTeam();
        team.setPlayer(staffFactory.createPlayer());
        team.setManager(staffFactory.createManager());
        return team;
    }

    protected abstract Team createTeam();
}
