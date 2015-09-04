package com.donkeigy.services;

import com.yahoo.objects.league.League;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cedric on 9/4/15.
 */
@Service("leagueService")
public class LeagueService
{
    @Autowired
    YahooDataService yahooDataService;

    public List<League> retrieveUserLeagues()
    {
        YahooServiceFactory factory = yahooDataService.getFactory();
        com.yahoo.services.LeagueService leagueService = (com.yahoo.services.LeagueService)factory.getService(ServiceType.LEAGUE);
        return leagueService.getUserLeagues("nfl");
    }
}
