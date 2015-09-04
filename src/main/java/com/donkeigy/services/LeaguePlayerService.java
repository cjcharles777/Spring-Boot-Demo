package com.donkeigy.services;

import com.donkeigy.dao.LeaguePlayersDAO;
import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.yahoo.objects.players.Player;
import com.yahoo.services.PlayerService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cedric on 9/4/15.
 */
@Service("leaugePlayerService")
public class LeaguePlayerService
{
    @Autowired
    YahooDataService yahooDataService;
    @Autowired
    LeaguePlayersDAO leaguePlayersDAO;

    public void loadPlayers(String leagueId)
    {
        YahooServiceFactory factory = yahooDataService.getFactory();
        PlayerService playerService = (PlayerService)factory.getService(ServiceType.PLAYER);
        List<Player> players = playerService.retriveLeaugePlayers(leagueId);
        List<LeaguePlayer> dbPlayers = new LinkedList<LeaguePlayer>();
        for(Player player : players)
        {
            dbPlayers.add(new LeaguePlayer(player, leagueId));
        }
        LeaguePlayer playerExample = new LeaguePlayer(leagueId);
        List<LeaguePlayer> oldPlayers =  leaguePlayersDAO.getLeaguePlayers(playerExample);
        for(LeaguePlayer oldPlayer : oldPlayers)
        {
            leaguePlayersDAO.deleteLeaguePlayer(oldPlayer);
        }

        leaguePlayersDAO.saveLeaguePlayers(dbPlayers);
    }
    public List<LeaguePlayer> getLeaguePlayersbyExample(LeaguePlayer example)
    {
        return leaguePlayersDAO.getLeaguePlayers(example);
    }
}
