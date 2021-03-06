package com.donkeigy.services;

import com.donkeigy.objects.analysis.*;
import com.donkeigy.objects.hibernate.LeaguePlayer;
import com.yahoo.objects.league.League;
import com.yahoo.objects.league.LeagueRosterPosition;
import com.yahoo.objects.league.LeagueSettings;
import com.yahoo.objects.league.transactions.LeagueTransaction;
import com.yahoo.objects.league.transactions.TransactionData;
import com.yahoo.objects.league.transactions.TransactionPlayer;
import com.yahoo.objects.players.Player;
import com.yahoo.objects.team.RosterStats;
import com.yahoo.objects.team.Team;
import com.yahoo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 9/29/15.
 */
@Service("analysisService")
public class AnalysisService
{
    @Autowired
    LeagueService leagueService;
    @Autowired
    TeamService teamService;
    @Autowired
    LeaguePlayerService leaguePlayerService;


    public LeagueAnalysis retrieveLeagueAnalysis (String leagueId)
    {
        LeagueAnalysis result = new LeagueAnalysis();
        Map<String,PositionAvg> positionAvgMap =  new HashMap<>();
        League league = leagueService.retrieveLeague(leagueId);
        List<Team> teams = teamService.retrieveLeagueTeams(leagueId);
        Map<String, Team> teamMap = new HashMap<>();
        List<PlayerPerformance> playerPerformancesList = new LinkedList<>();
        LeagueSettings leagueSettings = leagueService.retrieveLeagueSettings(leagueId);
        result.setAvailablePositonsArray(createPositionsArray(leagueSettings.getRoster_positions().getRoster_position()));
        List<TeamPositionAvg> teamPositionAvgList = new LinkedList<>();
        int currentWeek = Integer.parseInt(league.getCurrent_week());
        for(Team team : teams)
        {
            TeamPositionAvg teamPositionAvg = getTeamPositionAvg(currentWeek, team);
            teamPositionAvgList.add(teamPositionAvg);
            for(PositionAvg positionAvg : teamPositionAvg.getPositionAvgList())
            {
                PositionAvg tmpPositionAvg;
                if(positionAvgMap.containsKey(positionAvg.getPosition()))
                {
                    tmpPositionAvg = positionAvgMap.get(positionAvg.getPosition());
                    Integer count = tmpPositionAvg.getCount() + positionAvg.getCount();
                    BigDecimal total = tmpPositionAvg.getTotal().add(positionAvg.getTotal());
                    BigDecimal avg = total.divide(new BigDecimal(count), 4, RoundingMode.HALF_UP);
                    tmpPositionAvg.setAvg(avg);
                    tmpPositionAvg.setCount(count);
                    tmpPositionAvg.setTotal(total);

                }
                else
                {
                    tmpPositionAvg = new PositionAvg(positionAvg.getPosition(),positionAvg.getAvg(),positionAvg.getTotal(),positionAvg.getCount());
                }

                positionAvgMap.put(positionAvg.getPosition(),tmpPositionAvg);
            }
            teamMap.put(team.getTeam_key(), team);
            Map<String, PlayerPerformance> playerPerformanceMap = retrievePlayerPerformances(currentWeek, team, leagueId);
            playerPerformancesList.addAll(playerPerformanceMap.values());
        }
        List<PositionAvg> leaguePositionAvgList = new LinkedList<>();
        leaguePositionAvgList.addAll(positionAvgMap.values());
        result.setPositionAvgList(leaguePositionAvgList);
        result.setTeamPositionAvgList(teamPositionAvgList);
        result.setPlayerPerformanceList(playerPerformancesList);

        List<TeamPositionMovement> teamPositionMovementList = analyzeLeagueTransactions(leagueId, teamMap);
        result.setTeamPositionMovementList(teamPositionMovementList);
        Map<String, PositionMovements> positionMovementsMap = new HashMap<>();
        for(TeamPositionMovement tpm : teamPositionMovementList)
        {
            for(PositionMovements movements : tpm.getPositionMovementsList())
            {
                PositionMovements leaugePositionMovements;
                if(positionMovementsMap.containsKey(movements.getPosition()))
                {
                    leaugePositionMovements =  positionMovementsMap.get(movements.getPosition());
                }
                else
                {
                    leaugePositionMovements = new PositionMovements(movements.getPosition(), 0, 0, 0);
                }
                leaugePositionMovements.setAdd(leaugePositionMovements.getAdd() + movements.getAdd());
                leaugePositionMovements.setDrop(leaugePositionMovements.getDrop() + movements.getDrop());
                leaugePositionMovements.setTrades(leaugePositionMovements.getTrades() + movements.getTrades());
                positionMovementsMap.put(movements.getPosition(),leaugePositionMovements);
            }
        }
        List<PositionMovements> resultLeaugePositionMovementList = new LinkedList<>();
        resultLeaugePositionMovementList.addAll(positionMovementsMap.values());
        result.setPositionMovementsList(resultLeaugePositionMovementList);


        return result;
     }

    public List <ComparablePlayer> retrieveComparablePlayersinLeauge (String leagueId, String playerId)
    {
        League league = leagueService.retrieveLeague(leagueId);
        List <Team> teams = teamService.retrieveLeagueTeams(leagueId);
        LeaguePlayer comparedPlayer = leaguePlayerService.getLeaguePlayersbyLeagueIdandPlayerId(leagueId, playerId);
        int currentWeek = Integer.parseInt(league.getCurrent_week());
        List<ComparablePlayer> results = new LinkedList<>();
        for(Team team : teams)
        {

            List<RosterStats> rosterStatsList = teamService.retrieveWeeklyRosterPoints(team.getTeam_key(), currentWeek);
            for (RosterStats rosterStats : rosterStatsList)
            {
                String playerPosition = rosterStats.getSelectedPosition();;
                LeaguePlayer player = leaguePlayerService.getLeaguePlayersbyLeagueIdandPlayerId(leagueId, rosterStats.getPlayerKey());
                if(player.getDisplay_position().equals(comparedPlayer.getDisplay_position()))
                {
                    results.add(new ComparablePlayer(player, team, playerPosition));
                }



            }
        }
        return results;
    }
    private  List<TeamPositionMovement> analyzeLeagueTransactions(String leagueId, Map<String, Team> teamMap)
    {
        List<TeamPositionMovement> result = new LinkedList<>();
        List<LeagueTransaction> leagueTransactionList = leagueService.retrieveLeagueTransactions(leagueId);
        Map<String, Map<String, PositionMovements>> teamPositionMovementsMap = new HashMap<>();
        for (LeagueTransaction transaction : leagueTransactionList)
        {

            if(transaction.getPlayers() != null)
            {
                List<TransactionPlayer> transactionPlayerList = transaction.getPlayers().getPlayer();
                for (TransactionPlayer transactionPlayer : transactionPlayerList)
                {

                    String position = transactionPlayer.getDisplay_position();
                    TransactionData transactionData = transactionPlayer.getTransaction_data();
                    boolean isDrop = false;
                    boolean isAdd = false;
                    boolean isTrade = false;
                    List<String> teamids  =  new LinkedList<>();

                    if(transactionData.getType().equals("drop"))
                    {
                        isDrop = true;
                        teamids.add(transactionData.getSource_team_key());
                    }
                    if(transactionData.getType().equals("add"))
                    {
                        isAdd = true;
                        teamids.add(transactionData.getDestination_team_key());
                    }
                    if(transactionData.getType().equals("trade"))
                    {
                        isTrade = true;
                        teamids.add(transactionData.getSource_team_key());
                        teamids.add(transactionData.getDestination_team_key());
                    }

                    for(String teamid : teamids)
                    {
                        Map<String, PositionMovements> positionMovementsMap;
                        if(teamPositionMovementsMap.containsKey(teamid))
                        {
                            positionMovementsMap = teamPositionMovementsMap.get(teamid);

                        }
                        else
                        {
                            positionMovementsMap = new HashMap<>();
                        }

                        PositionMovements positionMovements;
                        if(positionMovementsMap.containsKey(position))
                        {
                            positionMovements = positionMovementsMap.get(position);
                        }
                        else
                        {
                            positionMovements = new PositionMovements(position, 0, 0 ,0);

                        }

                        if(isAdd)
                        {
                            positionMovements.setAdd(positionMovements.getAdd() + 1);
                        }
                        if(isDrop)
                        {
                            positionMovements.setDrop(positionMovements.getDrop() + 1);
                        }
                        if(isTrade)
                        {
                            positionMovements.setTrades(positionMovements.getTrades() + 1);
                        }

                        positionMovementsMap.put(position, positionMovements);
                        teamPositionMovementsMap.put(teamid, positionMovementsMap);
                    }
                }
            }
        }
        for(String teamid : teamPositionMovementsMap.keySet())
        {
            if(teamMap.containsKey(teamid))
            {
                Team team = teamMap.get(teamid);
                List<PositionMovements> positionMovementsList = new LinkedList<>();
                positionMovementsList.addAll(teamPositionMovementsMap.get(teamid).values());
                TeamPositionMovement teamPositionMovement = new TeamPositionMovement(team, positionMovementsList);
                result.add(teamPositionMovement);
            }

        }
        return result;

    }

    private TeamPositionAvg getTeamPositionAvg(int currentWeek, Team team)
    {
        TeamPositionAvg result = new TeamPositionAvg(team);
        Map<String, BigDecimal> positionTotalPointsMap = new HashMap<String, BigDecimal>();
        Map<String, Integer> positionCountMap = new HashMap<String, Integer>();
        for (int i = 1; i < currentWeek; i++)
        {
            List<RosterStats> rosterStatsList = teamService.retrieveWeeklyRosterPoints(team.getTeam_key(),i);
            for (RosterStats rosterStats : rosterStatsList)
            {
                BigDecimal points = rosterStats.getPlayerPoints();
                String playerPosition = rosterStats.getSelectedPosition();

                if(!playerPosition.equals("IR"))
                {
                    BigDecimal positionTotalSum;
                    Integer count;
                    if (!positionTotalPointsMap.containsKey(playerPosition)) {
                        positionTotalSum = new BigDecimal(0);
                        count = 0;
                    } else {
                        positionTotalSum = positionTotalPointsMap.get(playerPosition);
                        count = positionCountMap.get(playerPosition);
                    }
                    positionTotalSum = positionTotalSum.add(points);
                    count++;
                    positionTotalPointsMap.put(playerPosition, positionTotalSum);
                    positionCountMap.put(playerPosition, count);
                }



            }
        }

        List<PositionAvg> positionAvgs =  new LinkedList<>();
        for(String key : positionTotalPointsMap.keySet())
        {

            String position = key;
            BigDecimal total = positionTotalPointsMap.get(key);
            Integer count = positionCountMap.get(key);
            BigDecimal avg = total.divide(new BigDecimal(count),4, RoundingMode.HALF_UP);
            PositionAvg positionAvg = new PositionAvg(position, avg, total, count);
            positionAvgs.add(positionAvg);
        }
        result.setPositionAvgList(positionAvgs);
        return result;
    }

    private Map<String, PlayerPerformance> retrievePlayerPerformances(int currentWeek, Team team, String leagueId)
    {
        Map<String, PlayerPerformance> result = new HashMap<>();
        for (int i = 1; i < currentWeek; i++)
        {
            List<RosterStats> rosterStatsList = teamService.retrieveWeeklyRosterPoints(team.getTeam_key(),i);
            for (RosterStats rosterStats : rosterStatsList)
            {
                BigDecimal points = rosterStats.getPlayerPoints();
                String playerPosition = rosterStats.getSelectedPosition();
                if(!result.containsKey(rosterStats.getPlayerKey()))
                {

                    LeaguePlayer examplePlayer = new LeaguePlayer(leagueId);
                    examplePlayer.setPlayer_key(rosterStats.getPlayerKey());
                    List<LeaguePlayer> players = leaguePlayerService.getLeaguePlayersbyExample(examplePlayer);
                    LeaguePlayer actualPlayer = null;
                    if(players.size() > 0)
                    {
                        actualPlayer = players.get(0);
                    }
                    else
                    {
                        System.out.println(rosterStats.getPlayerKey());
                    }

                    result.put(rosterStats.getPlayerKey(), new PlayerPerformance(actualPlayer, new BigDecimal(0),new BigDecimal(0),new BigDecimal(0), team));
                }
                PlayerPerformance playerPerformance = result.get(rosterStats.getPlayerKey());
                BigDecimal totalPoints = playerPerformance.getPoints();
                totalPoints = totalPoints.add(points);
                playerPerformance.setPoints(totalPoints);
                if(playerPosition.equals("IR") || playerPosition.equals("BN"))
                {
                    BigDecimal nonEffectivePts =  playerPerformance.getNoneffectivePoints();
                    nonEffectivePts = nonEffectivePts.add(points);
                    playerPerformance.setNoneffectivePoints(nonEffectivePts);
                }
                else
                {
                    BigDecimal effectivePts = playerPerformance.getEffectivePoints();
                    effectivePts = effectivePts.add(points);
                    playerPerformance.setEffectivePoints(effectivePts);
                }
                result.put(rosterStats.getPlayerKey(), playerPerformance);
            }
        }
        return result;
    }
/**
    private List<TeamPositionComparableWeek> retrieveTeamPositionComparableWeekComparison(int currentWeek, Team team1, Team team2, String position, String leagueId)
    {
        Map<String, TeamPositionComparableWeek> result = new HashMap<>();
        Map<String, LeaguePlayer> playerMap = new HashMap<>();
        for (int i = 1; i < currentWeek; i++)
        {
            List<RosterStats> rosterStatsList = teamService.retrieveWeeklyRosterPoints(team1.getTeam_key(),i);
            rosterStatsList.addAll(teamService.retrieveWeeklyRosterPoints(team2.getTeam_key(),i));
            TeamPositionComparableWeek teamPositionComparableWeek = new TeamPositionComparableWeek();
            teamPositionComparableWeek.setWeek(i);

            for (RosterStats rosterStats : rosterStatsList)
            {

                LeaguePlayer actualPlayer = null;
                String playerPosition = rosterStats.getSelectedPosition();
                if(!playerMap.containsKey(rosterStats.getPlayerKey()))
                {

                    LeaguePlayer examplePlayer = new LeaguePlayer(leagueId);
                    examplePlayer.setPlayer_key(rosterStats.getPlayerKey());
                    List<LeaguePlayer> players = leaguePlayerService.getLeaguePlayersbyExample(examplePlayer);
                    if(players.size() > 0)
                    {
                        actualPlayer = players.get(0);
                    }
                    else
                    {
                        System.out.println(rosterStats.getPlayerKey());
                    }

                    playerMap.put(rosterStats.getPlayerKey(), actualPlayer);
                }
                else
                {
                    actualPlayer = playerMap.get(rosterStats.getPlayerKey());
                }
                if(playerPosition.equals(position))
                {
                    BigDecimal points = rosterStats.getPlayerPoints();

                }
                //TODO: Figure out what do you want.
                PlayerPerformance playerPerformance = null;
                BigDecimal totalPoints = playerPerformance.getPoints();
                totalPoints = totalPoints.add(points);
                playerPerformance.setPoints(totalPoints);
                if(playerPosition.equals("IR") || playerPosition.equals("BN"))
                {
                    BigDecimal nonEffectivePts =  playerPerformance.getNoneffectivePoints();
                    nonEffectivePts = nonEffectivePts.add(points);
                    playerPerformance.setNoneffectivePoints(nonEffectivePts);
                }
                else
                {
                    BigDecimal effectivePts = playerPerformance.getEffectivePoints();
                    effectivePts = effectivePts.add(points);
                    playerPerformance.setEffectivePoints(effectivePts);
                }
                result.put(rosterStats.getPlayerKey(), playerPerformance);
            }
        }
        return result;
    }
**/
    private String[] createPositionsArray(List<LeagueRosterPosition> roster_position)
    {
        String[] result = new String[roster_position.size()];
        for(int i = 0; i < roster_position.size(); i++)
        {
            result[i] = roster_position.get(i).getPosition();
        }
        return result;
    }
}
