package com.donkeigy.objects.analysis;

import com.yahoo.objects.team.Team;

import java.util.List;

/**
 * Created by cedric on 9/29/15.
 */
public class TeamPositionMovement
{
    private Team team;
    private List<PositionMovements> positionMovementsList;


    public TeamPositionMovement(Team team, List<PositionMovements> positionMovementsList) {
        this.team = team;
        this.positionMovementsList = positionMovementsList;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PositionMovements> getPositionMovementsList() {
        return positionMovementsList;
    }

    public void setPositionMovementsList(List<PositionMovements> positionMovementsList) {
        this.positionMovementsList = positionMovementsList;
    }
}
