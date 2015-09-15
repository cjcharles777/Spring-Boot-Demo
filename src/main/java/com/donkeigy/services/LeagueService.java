package com.donkeigy.services;

import com.yahoo.objects.league.League;
import com.yahoo.services.enums.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cedric on 9/4/15.
 */
@Service("leagueService")
public class LeagueService  extends BaseService
{
    @Autowired
    YahooDataService yahooDataService;
    com.yahoo.services.LeagueService leagueService;

    public List<League> retrieveUserLeagues()
    {

        return leagueService.getUserLeagues("nfl");
    }

    @Override
    public void initService()
    {
        leagueService = (com.yahoo.services.LeagueService)factory.getService(ServiceType.LEAGUE);
    }
}
