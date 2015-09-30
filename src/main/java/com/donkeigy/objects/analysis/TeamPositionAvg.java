package com.donkeigy.objects.analysis;

import com.yahoo.objects.team.Team;

import java.util.List;

/**
 * Created by cedric on 9/29/15.
 */
public class TeamPositionAvg
{
    private Team team;
    private List<PositionAvg> positionAvgList;

    public TeamPositionAvg( Team team)
    {

        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PositionAvg> getPositionAvgList() {
        return positionAvgList;
    }

    public void setPositionAvgList(List<PositionAvg> positionAvgList) {
        this.positionAvgList = positionAvgList;
    }
}
