package com.donkeigy.controllers;

import com.donkeigy.dao.LeaguePlayersDAO;
import com.donkeigy.objects.draft.adp.FantasyFootballADP;
import com.donkeigy.objects.draft.players.DrafteeInfo;
import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.donkeigy.services.YahooDataService;
import com.donkeigy.util.MFLDataLoad;
import com.yahoo.objects.league.League;
import com.yahoo.objects.players.Player;
import com.yahoo.services.LeagueService;
import com.yahoo.services.PlayerService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by cedric on 9/2/15.
 */
@Controller
@RequestMapping("/draft")
public class DraftController
{

    @Autowired
    YahooDataService yahooDataService;
    @Autowired
    LeaguePlayersDAO leaguePlayersDAO;
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

            return "draft";
        }
        else
        {
            model.addAttribute("oAuthUrl", yahooDataService.retrieveAuthUrl());
            return "oauth-verify";
        }
        //yahooDataService.init();


    }
    @ResponseBody
    @RequestMapping(value="/retrieve/mfl/{leagueKey}",method= RequestMethod.GET )
    public List<DrafteeInfo> retrievePlayers(@PathVariable("leagueKey") String leagueKey)
    {
        List<DrafteeInfo> result = new LinkedList<>();
        Map<String, FantasyFootballADP> averageDraftPositionMap = new HashMap<>();
        MFLDataLoad mplDataLoad = new MFLDataLoad(leaguePlayersDAO);
        averageDraftPositionMap.putAll(mplDataLoad.getAdpMap()); // draft Map
        LeaguePlayer playerExample = new LeaguePlayer(leagueKey);
        List<LeaguePlayer> leaguePlayers = leaguePlayersDAO.getLeaguePlayers(playerExample); //players list
        for(LeaguePlayer player : leaguePlayers)
        {
           result.add(new DrafteeInfo(player, averageDraftPositionMap.get(player.getPlayer_id())));
        }
        return result;
    }
}
