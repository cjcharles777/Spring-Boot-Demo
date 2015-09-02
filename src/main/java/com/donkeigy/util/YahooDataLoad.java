package com.donkeigy.util;


import com.donkeigy.dao.LeaguePlayersDAO;
import com.donkeigy.objects.draft.adp.FantasyFootballADP;
import com.donkeigy.objects.draft.adp.YahooAverageDraftPosition;
import com.donkeigy.objects.draft.players.FantasyFootBallPlayer;
import com.donkeigy.objects.draft.players.YahooFantasyLeaugePlayer;
import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.donkeigy.util.FFDataLoad;
import com.yahoo.objects.players.Name;
import com.yahoo.objects.players.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by cedric on 9/5/14.
 */
public class YahooDataLoad extends FFDataLoad
{

    public YahooDataLoad(LeaguePlayersDAO playersDAO)
    {
        super(playersDAO);
        loadData();
    }

    @Override
    protected void loadData()
    {
        String url = "http://football.fantasysports.yahoo.com/f1/draftanalysis";
        playerList = new LinkedList<FantasyFootBallPlayer>();
        playerIDMap = new HashMap<String, Player>();
        adpMap = new HashMap<String, FantasyFootballADP>();
      try
      {
          boolean next;

          do
          {
              Document d = Jsoup.connect(url).get();
              retrieveDataFromPage(d);
              Element nextPageElement = d.select("ul.pagingnavlist").last().select("li.last.Inlineblock").first();
              next = (!nextPageElement.select("a").isEmpty());
              if(next)
              {
                  Element nextUrlElement = nextPageElement.select("a").first();
                  url = nextUrlElement.attr("abs:href");
              }

          }
          while(next);
      }
      catch (Exception e)
      {
          Logger.getLogger(YahooDataLoad.class.getName()).log(Level.SEVERE, null, e);
      }
    }
    private void retrieveDataFromPage(Document d)
    {
        Elements cardElements = d.select("table#draftanalysistable tbody tr");
        for (Element cardElement : cardElements)
        {
            Elements tdElements = cardElement.select("td");
            Element playerElement = tdElements.get(0);
            Player rowPlayer = extractPlayerfromElement(playerElement);
            if(rowPlayer != null)
            {
                playerIDMap.put(rowPlayer.getPlayer_id(), rowPlayer);
                playerList.add(new YahooFantasyLeaugePlayer(rowPlayer));
                Element avgPickElement = tdElements.get(1);
                Element percentDraftedElement = tdElements.get(3);

                String percentageTxt = percentDraftedElement.text();
                percentageTxt = percentageTxt.substring(0, percentageTxt.indexOf("%"));

                YahooAverageDraftPosition yadp = new YahooAverageDraftPosition();
                yadp.setAdp(new BigDecimal(avgPickElement.text()));
                yadp.setPercentageOfDrafts((new BigDecimal(percentageTxt)).divide(new BigDecimal(100)));
                adpMap.put(rowPlayer.getPlayer_id(), yadp);
            }
        }
    }
    private Player extractPlayerfromElement(Element e)
    {
        Elements playerNameElements = e.select("div div div a");
        Elements teamPositionElements = e.select("div div div span");
        LeaguePlayer tmpPlayer = new LeaguePlayer();
        if(!e.select("div div div a").isEmpty())
        {
            Name tmpPlayerName = new Name();
            tmpPlayerName.setFull(playerNameElements.first().text());
            tmpPlayer.setName(tmpPlayerName);
        }
        if(!teamPositionElements.isEmpty())
        {
            String teamPositionString = teamPositionElements.first().text();
            String[] teamPositionStringArr = teamPositionString.split("-");
            tmpPlayer.setEditorial_team_abbr(teamPositionStringArr[0].trim());
            if(!teamPositionStringArr[1].trim().equals("TE"))
            {
                tmpPlayer.setDisplay_position(teamPositionStringArr[1].trim());
            }
        }
        List<LeaguePlayer> results = playersDAO.getLeaguePlayers(tmpPlayer);
        if(!results.isEmpty())
        {
            return results.get(0);
        }
        else
        {
            System.out.println("Get for Player : "+ tmpPlayer.getName().getFull() +" Position: "+tmpPlayer.getDisplay_position() +"returned nothing");
           // System.out.println("Get for Position : "+ tmpPlayer.getName().getFull() +" returned nothing");
            return null;

        }
    }
}
