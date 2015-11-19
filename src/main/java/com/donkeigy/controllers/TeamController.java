package com.donkeigy.controllers;

import com.donkeigy.services.TeamService;
import com.donkeigy.services.YahooDataService;
import com.yahoo.objects.team.Roster;
import com.yahoo.objects.team.RosterStats;
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
    @RequestMapping(value="/retrieve/league/{leagueKey}/self",method= RequestMethod.GET )
    public Team retrieveMyLeagueTeam(@PathVariable("leagueKey") String leagueKey)
    {

        List<Team> leagueTeams =  teamService.retrieveLeagueTeams(leagueKey);
        for(Team team : leagueTeams)
        {
            if(team.getIs_owned_by_current_login() != null && team.getIs_owned_by_current_login().equals("1"))
            {
                return team;
            }

        }
        return null;
    }
    @RequestMapping(value="/retrieve/roster/{teamKey}/{week}",method= RequestMethod.GET )
    public Roster retrieveTeamRoster(@PathVariable("teamKey") String teamKey, @PathVariable("week") Integer week)
    {
        return teamService.retrieveTeamRoster(teamKey, week);
    }
    @RequestMapping(value="/retrieve/stats/{teamKey}/{week}",method= RequestMethod.GET )
    public List<RosterStats> retrieveWeeklyRosterPoints(@PathVariable("teamKey") String teamKey, @PathVariable("week") Integer week)
    {
        return teamService.retrieveWeeklyRosterPoints(teamKey,week);
    }
}
