package com.donkeigy.controllers;

import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.donkeigy.services.LeaguePlayerService;
import com.yahoo.objects.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cedric on 8/27/15.
 */
@RestController
@RequestMapping("/players")
public class PlayerController
{
    @Autowired
    private LeaguePlayerService leaguePlayerService;

    @RequestMapping(value="/retrieve/league/{leagueKey}",method= RequestMethod.GET )
    public List<LeaguePlayer> retrievePlayers(@PathVariable("leagueKey") String leagueKey)
    {
        LeaguePlayer playerExample = new LeaguePlayer(leagueKey);
        return leaguePlayerService.getLeaguePlayersbyExample(playerExample);
    }

    @RequestMapping(value="/retrieve/league/{leagueKey}/position/{position}",method= RequestMethod.GET )
    public List<LeaguePlayer> retrievePlayers(@PathVariable("leagueKey") String leagueKey, @PathVariable("position") String position)
    {
        LeaguePlayer playerExample = new LeaguePlayer(leagueKey);
        playerExample.setDisplay_position(position);
        return leaguePlayerService.getLeaguePlayersbyExample(playerExample);
    }
}
