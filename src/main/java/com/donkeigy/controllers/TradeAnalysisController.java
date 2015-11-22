package com.donkeigy.controllers;

import com.donkeigy.services.YahooDataService;
import com.yahoo.objects.league.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 9/17/15.
 */
@Controller
@RequestMapping("/trade-analysis")
public class TradeAnalysisController
{
    @Autowired
    YahooDataService yahooDataService;
    @Autowired
    com.donkeigy.services.DraftService draftService;
    @Autowired
    com.donkeigy.services.LeagueService leagueService;

    private List<League> leagues =new ArrayList<League>();
    private Map<String, League> leagueKeyMap= new HashMap<>();

    @RequestMapping(value="/")
    public String loadHomePage(Model model)
    {


        if (yahooDataService.isConnected()) {
            leagues.addAll(leagueService.retrieveUserLeagues());
            for (League league : leagues) {
                leagueKeyMap.put(league.getLeague_key(), league);
            }
            model.addAttribute("leagues", leagues);

            return "trade-analysis";
        }
        else
        {
            model.addAttribute("oAuthUrl", yahooDataService.retrieveAuthUrl());
            return "oauth-verify";
        }
    }
}
