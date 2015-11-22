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
    private BigDecimal effectivePoints;
    private BigDecimal noneffectivePoints;
    private Team team;


    public PlayerPerformance(LeaguePlayer player, BigDecimal points, BigDecimal effectivePoints, BigDecimal noneffectivePoints, Team team) {
        this.player = player;
        this.points = points;
        this.effectivePoints = effectivePoints;
        this.noneffectivePoints = noneffectivePoints;
        this.team = team;
    }

    public PlayerPerformance(BigDecimal points) {
        this.points = points;
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

    public BigDecimal getEffectivePoints() {
        return effectivePoints;
    }

    public void setEffectivePoints(BigDecimal effectivePoints) {
        this.effectivePoints = effectivePoints;
    }

    public BigDecimal getNoneffectivePoints() {
        return noneffectivePoints;
    }

    public void setNoneffectivePoints(BigDecimal noneffectivePoints) {
        this.noneffectivePoints = noneffectivePoints;
    }
}
