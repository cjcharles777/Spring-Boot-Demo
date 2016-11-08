package com.donkeigy.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.donkeigy.services.LeaguePlayerService;
import com.donkeigy.services.YahooDataService;
import com.yahoo.objects.league.League;
import com.yahoo.services.LeagueService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;

/**
 * Created by cedric on 11/8/16.
 */
@Controller
public class BaseController
{
    @Autowired
    YahooDataService yahooDataService;
    @Autowired
    LeaguePlayerService leaguePlayerService;


    protected String loadHomePage(Model model, String page)
    {
        List<League> leagues = new ArrayList<>();
        if(yahooDataService.isConnected())
        {
            YahooServiceFactory factory = yahooDataService.getFactory();
            LeagueService leagueService = (LeagueService)factory.getService(ServiceType.LEAGUE);

            leagues = leagueService.getUserLeagues("nfl");

            leaguePlayerService.loadPlayers(leagues.get(0).getLeague_key());



            model.addAttribute("leagues", leagues);

            return page;
        }
        else
        {
            model.addAttribute("oAuthUrl", yahooDataService.retrieveAuthUrl());
            return "oauth-verify";
        }
        //yahooDataService.init();


    }

}
