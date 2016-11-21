package com.donkeigy.objects.draft.players;

import com.donkeigy.objects.draft.adp.FantasyFootballADP;
import com.donkeigy.objects.hibernate.LeaguePlayer;

/**
 * Created by cedric on 9/2/15.
 */
public class DrafteeInfo
{
    private LeaguePlayer player;
    private FantasyFootballADP adp;

    public DrafteeInfo() {
    }

    public DrafteeInfo(LeaguePlayer player, FantasyFootballADP adp) {
        this.player = player;
        this.adp = adp;
    }

    public LeaguePlayer getPlayer() {
        return player;
    }

    public void setPlayer(LeaguePlayer player) {
        this.player = player;
    }

    public FantasyFootballADP getAdp() {
        return adp;
    }

    public void setAdp(FantasyFootballADP adp) {
        this.adp = adp;
    }
}
