package com.donkeigy.services;

import com.donkeigy.objects.draft.picks.EnhancedDraftPick;
import com.yahoo.objects.draft.DraftPick;
import com.yahoo.objects.draft.DraftResults;
import com.yahoo.objects.players.Player;
import com.yahoo.objects.team.Team;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cedric on 9/4/15.
 */
@Service("draftService")
public class DraftService extends BaseService
{
    private com.yahoo.services.DraftService draftService;

    @PostConstruct
    protected void init()
    {
        super.init();
    }

    public List<EnhancedDraftPick> retrieveDraftResults(String leagueId)
    {


        DraftResults draftResults =  draftService.getDraftResults(leagueId);
        List<EnhancedDraftPick> results = new LinkedList<>();
        if(draftResults != null)
        {
            results.addAll(draftResults.getDraft_result().stream().map(this::enhanceDraftPick).collect(Collectors.toList()));
        }

        return results;
    }

    public EnhancedDraftPick enhanceDraftPick (DraftPick pick)
    {
        YahooServiceFactory factory = yahooDataService.getFactory();
        com.yahoo.services.PlayerService playerService = (com.yahoo.services.PlayerService)factory.getService(ServiceType.PLAYER);
        com.yahoo.services.TeamService teamService = (com.yahoo.services.TeamService)factory.getService(ServiceType.TEAM);
        Player player = playerService.getPlayer(pick.getPlayer_key());
        Team team = teamService.getTeam(pick.getTeam_key());
        return new EnhancedDraftPick(pick, team, player);
    }

    @Override
    public void initService() {
        draftService = (com.yahoo.services.DraftService)factory.getService(ServiceType.DRAFT);
    }
}
