package com.donkeigy.services;

import com.yahoo.objects.team.Team;
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
}
