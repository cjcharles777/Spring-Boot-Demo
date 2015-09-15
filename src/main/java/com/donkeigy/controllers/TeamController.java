package com.donkeigy.controllers;

import com.donkeigy.services.TeamService;
import com.donkeigy.services.YahooDataService;
import com.yahoo.objects.team.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cedric on 9/15/15.
 */
@RestController
@RequestMapping("/team")
public class TeamController
{

    @Autowired
    private TeamService teamService;

    @RequestMapping(value="/retrieve/league/{leagueKey}",method= RequestMethod.GET )
    public List<Team> retrieveLeagueTeams(@PathVariable("leagueKey") String leagueKey)
    {

        return teamService.retrieveLeagueTeams(leagueKey);
    }
}
