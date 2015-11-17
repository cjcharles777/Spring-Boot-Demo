package com.donkeigy.objects.analysis;

import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.yahoo.objects.team.Team;

import java.math.BigDecimal;

/**
 * Created by cedric on 11/17/15.
 */
public class PlayerPerformance
{
    private LeaguePlayer player;
    private BigDecimal points;
    private Team team;


    public PlayerPerformance(LeaguePlayer player, BigDecimal points, Team team) {
        this.player = player;
        this.points = points;
        this.team = team;
    }

    public LeaguePlayer getPlayer() {
        return player;
    }

    public void setPlayer(LeaguePlayer player) {
        this.player = player;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
