package com.donkeigy.controllers;

import com.donkeigy.services.LeaguePlayerService;
import com.yahoo.objects.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
