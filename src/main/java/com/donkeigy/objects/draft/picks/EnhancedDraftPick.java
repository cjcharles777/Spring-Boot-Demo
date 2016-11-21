package com.donkeigy.objects.draft.picks;

import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.yahoo.objects.draft.DraftPick;
import com.yahoo.objects.players.Player;
import com.yahoo.objects.team.Team;

/**
 * Created by cedric on 9/9/15.
 */
public class EnhancedDraftPick
{
    String pick;
    String round;
    Player player;
    Team team;

    public EnhancedDraftPick() {
    }

    public EnhancedDraftPick(DraftPick draftPick, Team team, Player player)
    {
        this.pick = draftPick.getPick();
        this.round = draftPick.getRound();
        this.team = team;
        this.player = player;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Player getPlayer() {
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
}
