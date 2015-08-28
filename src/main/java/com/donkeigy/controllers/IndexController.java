package com.donkeigy.controllers;

import com.donkeigy.services.YahooDataService;
import com.yahoo.objects.league.League;
import com.yahoo.objects.players.Player;
import com.yahoo.services.LeagueService;
import com.yahoo.services.PlayerService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cedric on 8/25/15.
 */
@Controller
public class IndexController
{

    @Autowired
    YahooDataService yahooDataService;
    private List<League> leagues =new ArrayList<League>();


    @RequestMapping(value="/")
    public String loadHomePage(Model model)
    {

        if(yahooDataService.isConnected())
        {
            YahooServiceFactory factory = yahooDataService.getFactory();
            LeagueService leagueService = (LeagueService)factory.getService(ServiceType.LEAGUE);

            leagues = leagueService.getUserLeagues("nfl");
            model.addAttribute("leagues", leagues);
            return "index";
        }
        else
        {
            model.addAttribute("oAuthUrl", yahooDataService.retrieveAuthUrl());
            return "oauth-verify";
        }
        //yahooDataService.init();


    }


    @RequestMapping(value="/addPlayer",method= RequestMethod.POST, consumes = "application/json")
    public String addPlayer(@ModelAttribute("player")Player player, Model model){

        //leagues.add(player);

        return "redirect:/";
    }

}
