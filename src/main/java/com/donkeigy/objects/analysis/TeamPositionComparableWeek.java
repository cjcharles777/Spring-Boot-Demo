package com.donkeigy.objects.analysis;

import java.math.BigDecimal;

import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.yahoo.objects.players.Player;

/**
 * Created by cedric on 11/8/16.
 */
public class TeamPositionComparableWeek
{
    private LeaguePlayer player1;
    private LeaguePlayer player2;
    private BigDecimal playerPoints1;
    private BigDecimal playerPoints2;
    private Integer week;

    public TeamPositionComparableWeek()
    {
    }

    public TeamPositionComparableWeek(LeaguePlayer player1, LeaguePlayer player2, BigDecimal playerPoints1, BigDecimal playerPoints2, Integer week) {
        this.player1 = player1;
        this.player2 = player2;
        this.playerPoints1 = playerPoints1;
        this.playerPoints2 = playerPoints2;
        this.week = week;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(LeaguePlayer player1) {
        this.player1 = player1;
    }

    public LeaguePlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(LeaguePlayer player2) {
        this.player2 = player2;
    }

    public BigDecimal getPlayerPoints1() {
        return playerPoints1;
    }

    public void setPlayerPoints1(BigDecimal playerPoints1) {
        this.playerPoints1 = playerPoints1;
    }

    public BigDecimal getPlayerPoints2() {
        return playerPoints2;
    }

    public void setPlayerPoints2(BigDecimal playerPoints2) {
        this.playerPoints2 = playerPoints2;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
