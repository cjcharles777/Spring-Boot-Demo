package com.donkeigy.services;

import com.yahoo.objects.draft.DraftResults;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cedric on 9/4/15.
 */
@Service("draftService")
public class DraftService
{
    @Autowired
    YahooDataService yahooDataService;

    public DraftResults retrieveDraftResults(String leagueId)
    {
        YahooServiceFactory factory = yahooDataService.getFactory();
        com.yahoo.services.DraftService draftService = (com.yahoo.services.DraftService)factory.getService(ServiceType.DRAFT);
        return draftService.getDraftResults(leagueId);
    }
}
