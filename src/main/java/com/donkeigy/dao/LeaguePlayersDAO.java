/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.dao;



import com.donkeigy.objects.hibernate.LeaguePlayer;

import java.util.List;

/**
 *
 * @author cedric
 */
public interface LeaguePlayersDAO 
{
    public void saveLeaguePlayer(LeaguePlayer player);
    public void saveLeaguePlayers(List<LeaguePlayer> players);
    public List<LeaguePlayer> getAllLeaguePlayers();
    public List<LeaguePlayer> getLeaguePlayers(int firstResult, int maxResults);
    public List<LeaguePlayer> getLeaguePlayers(LeaguePlayer p);
    public LeaguePlayer getLeaguePlayerById(int playerId);
    public LeaguePlayer getLeaguePlayerbyYahooId(int yahooId);
    public void deleteLeaguePlayer(LeaguePlayer player);
    public void clearLeaguePlayers();
}
