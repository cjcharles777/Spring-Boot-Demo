package com.donkeigy.objects.analysis;

import java.util.List;

/**
 * Created by cedric on 9/29/15.
 */
public class LeagueAnalysis
{
    private List<PositionAvg> positionAvgList;
    private List<PositionMovements> positionMovementsList;
    private List<TeamPositionAvg> teamPositionAvgList;
    private String[] availablePositonsArray;
    private List<TeamPositionMovement> TeamPositionMovementList;

    public List<PositionAvg> getPositionAvgList() {
        return positionAvgList;
    }

    public void setPositionAvgList(List<PositionAvg> positionAvgList) {
        this.positionAvgList = positionAvgList;
    }

    public List<TeamPositionAvg> getTeamPositionAvgList() {
        return teamPositionAvgList;
    }

    public void setTeamPositionAvgList(List<TeamPositionAvg> teamPositionAvgList) {
        this.teamPositionAvgList = teamPositionAvgList;
    }

    public String[] getAvailablePositonsArray() {
        return availablePositonsArray;
    }

    public void setAvailablePositonsArray(String[] availablePositonsArray) {
        this.availablePositonsArray = availablePositonsArray;
    }

    public List<PositionMovements> getPositionMovementsList() {
        return positionMovementsList;
    }

    public void setPositionMovementsList(List<PositionMovements> positionMovementsList) {
        this.positionMovementsList = positionMovementsList;
    }

    public List<TeamPositionMovement> getTeamPositionMovementList() {
        return TeamPositionMovementList;
    }

    public void setTeamPositionMovementList(List<TeamPositionMovement> teamPositionMovementList) {
        TeamPositionMovementList = teamPositionMovementList;
    }
}
