package com.donkeigy.services;

import com.yahoo.objects.team.Roster;
import com.yahoo.objects.team.RosterStats;
import com.yahoo.objects.team.Team;
import com.yahoo.objects.team.TeamStat;
import com.yahoo.services.enums.ServiceType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by cedric on 9/15/15.
 */
@Service("teamService")
public class TeamService extends BaseService
{

    private com.yahoo.services.TeamService teamService;


    @Override
    protected void initService()
    {
        teamService = (com.yahoo.services.TeamService)factory.getService(ServiceType.TEAM);
    }

    public List<Team> retrieveLeagueTeams (String leagueKey)
    {
        return teamService.getLeagueTeams(leagueKey);
    }
    public Roster retrieveTeamRoster (String teamKey, int week)
    {
        return teamService.getTeamRoster(teamKey,week);
    }
    public List<RosterStats>retrieveWeeklyRosterPoints(String teamKey, int week)
    {
        return teamService.getWeeklyTeamRosterPoints(teamKey, week);
    }
    public List<TeamStat> retrieveWeeklyTeamPointsForSeason(String teamKey)
    {
        return teamService.getWeeklyTeamPointsForSeason(teamKey);
    }
}
