package com.donkeigy.controllers;

import com.donkeigy.dao.LeaguePlayersDAO;
import com.donkeigy.objects.OAuthVerifyToken;
import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.donkeigy.services.YahooDataService;
import com.yahoo.objects.league.League;
import com.yahoo.objects.players.Player;
import com.yahoo.services.LeagueService;
import com.yahoo.services.PlayerService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cedric on 8/27/15.
 */
@RestController
@RequestMapping("/players")
public class PlayerController
{
    @Autowired
    YahooDataService yahooDataService;
    @Autowired
    LeaguePlayersDAO leaguePlayersDAO;

    @RequestMapping(value="/load",method= RequestMethod.POST, consumes = "application/json")
    public String loadPlayers(@RequestBody final League request)
    {
        YahooServiceFactory factory = yahooDataService.getFactory();
        PlayerService playerService = (PlayerService)factory.getService(ServiceType.PLAYER);
        List<Player> players = playerService.retriveLeaugePlayers(request.getLeague_id());
        List<LeaguePlayer> dbPlayers = new LinkedList<LeaguePlayer>();
        for(Player player : players)
        {
            dbPlayers.add(new LeaguePlayer(player, request.getLeague_id()));
        }

        leaguePlayersDAO.saveLeaguePlayers(dbPlayers);

        //leagues.add(player);

        return "redirect:/";
    }

}
