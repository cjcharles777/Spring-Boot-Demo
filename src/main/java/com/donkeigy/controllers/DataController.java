package com.donkeigy.controllers;

import com.donkeigy.objects.analysis.ComparablePlayer;
import com.donkeigy.objects.analysis.LeagueAnalysis;
import com.donkeigy.services.AnalysisService;
import com.donkeigy.services.LeaguePlayerService;
import com.donkeigy.services.LeagueService;
import com.yahoo.objects.league.League;
import com.yahoo.objects.league.LeagueRosterPosition;
import com.yahoo.objects.league.LeagueSettings;
import com.yahoo.objects.league.transactions.LeagueScoreboard;
import com.yahoo.objects.league.transactions.LeagueTransaction;
import com.yahoo.objects.team.Team;
import com.donkeigy.services.TeamService;
import com.yahoo.objects.team.TeamStat;
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
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value="/load",method= RequestMethod.POST, consumes = "application/json")
    public String loadData(@RequestBody final League request)
    {

        leaguePlayerService.loadPlayers(request.getLeague_id());

        //leagues.add(player);

        return "redirect:/";
    }
    @RequestMapping(value="/analysis/league/{leagueKey}",method= RequestMethod.GET )
    public LeagueAnalysis retrieveLeagueAnalysis(@PathVariable("leagueKey") String leagueKey)
    {

        return analysisService.retrieveLeagueAnalysis(leagueKey);
    }

    @RequestMapping(value="/analysis/league/transactions/{leagueKey}",method= RequestMethod.GET )
    public List<LeagueTransaction> retrieveLeagueTransactions(@PathVariable("leagueKey") String leagueKey)
    {

        return leagueService.retrieveLeagueTransactions(leagueKey);
    }

    @RequestMapping(value="/analysis/team/season/points/{teamKey}",method= RequestMethod.GET )
    public List<TeamStat> retrieveSeasonPointsForTeam(@PathVariable("teamKey") String teamKey)
    {

        return teamService.retrieveWeeklyTeamPointsForSeason(teamKey);
    }
    @RequestMapping(value="/league/{leagueKey}",method= RequestMethod.GET )
    public League retrieveLeagueInformation(@PathVariable("leagueKey") String leagueKey)
    {

        return leagueService.retrieveLeague(leagueKey);
    }
    @RequestMapping(value="/league/{leagueKey}/roster-positions",method= RequestMethod.GET )
    public List<LeagueRosterPosition> retrieveLeagueRosterPositions(@PathVariable("leagueKey") String leagueKey)
    {

        return leagueService.retrieveLeagueRosterPositions(leagueKey);
    }

    @RequestMapping(value="/league/scoreboard/{leagueKey}/{week}",method= RequestMethod.GET )
    public LeagueScoreboard retrieveLeagueScoreboard(@PathVariable("leagueKey") String leagueKey, @PathVariable("week") Integer week)
    {

        return leagueService.retrieveWeeklyScoreBoard(leagueKey, week);
    }
    @RequestMapping(value="/players/comparable/{leagueKey}/{playerid}",method= RequestMethod.GET )
    public List<ComparablePlayer> retrieveComprableLeaugePlayers(@PathVariable("leagueKey") String leagueKey, @PathVariable("playerid") String playerid)
    {
        return analysisService.retrieveComparablePlayersinLeauge(leagueKey, playerid);
    }

}
