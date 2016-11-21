package com.donkeigy.controllers;

import com.donkeigy.dao.LeaguePlayersDAO;
import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.donkeigy.services.LeaguePlayerService;
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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cedric on 8/25/15.
 */
@Controller
public class IndexController extends BaseController
{

    @Autowired
    YahooDataService yahooDataService;
    @Autowired
    LeaguePlayerService leaguePlayerService;
    @Autowired
    LeaguePlayersDAO leaguePlayersDAO;
    private List<League> leagues =new ArrayList<League>();


    @RequestMapping(value="/")
    public String loadHomePage(Model model)
    {

        return super.loadHomePage(model, "index");



    }


    @RequestMapping(value="/addPlayer",method= RequestMethod.POST, consumes = "application/json")
    public String addPlayer(@ModelAttribute("player")Player player, Model model){

        //leagues.add(player);

        return "redirect:/";
    }

}
