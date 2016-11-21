package com.donkeigy.objects.analysis;

import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.yahoo.objects.team.Team;

import java.math.BigDecimal;

/**
 * Created by cedric on 11/24/15.
 */
public class ComparablePlayer
{
    private LeaguePlayer player;
    private Team team;
    private String currentPosition;

    public ComparablePlayer(LeaguePlayer player, Team team, String currentPosition) {
        this.player = player;
        this.team = team;
        this.currentPosition = currentPosition;
    }

    public LeaguePlayer getPlayer() {
        return player;
    }

    public void setPlayer(LeaguePlayer player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }
}
