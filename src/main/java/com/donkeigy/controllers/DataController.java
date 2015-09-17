package com.donkeigy.controllers;

import com.donkeigy.services.LeaguePlayerService;
import com.yahoo.objects.league.League;
import com.yahoo.objects.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cedric on 9/4/15.
 */
@RestController
@RequestMapping("/data")
public class DataController
{
    @Autowired
    private LeaguePlayerService leaguePlayerService;

    @RequestMapping(value="/load",method= RequestMethod.POST, consumes = "application/json")
    public String loadData(@RequestBody final League request)
    {

        leaguePlayerService.loadPlayers(request.getLeague_id());

        //leagues.add(player);

        return "redirect:/";
    }
    @RequestMapping(value="/analysis/league/players/{leagueKey}",method= RequestMethod.GET )
    public List<String[]> retrieveLeaguePlayerAnalysis(@PathVariable("leagueKey") String leagueKey)
    {

        return null;
    }

}
