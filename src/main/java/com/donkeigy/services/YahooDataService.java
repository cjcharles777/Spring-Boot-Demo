package com.donkeigy.services;

import com.yahoo.engine.YahooFantasyEngine;
import com.yahoo.objects.api.YahooApiInfo;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.utils.oauth.OAuthConnection;
import com.yahoo.utils.yql.YQLQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by cedric on 8/25/15.
 */

@Repository("yahooDataService")
public class YahooDataService
{

    @Autowired
    OAuthConnection oauthConnection;
    @Autowired
    YQLQueryUtil yqlQueryUtil;
    @Autowired
    YahooServiceFactory factory;
    public YahooDataService()
    {

    }

    public void init()
    {
        YahooApiInfo info =
                new YahooApiInfo("dj0yJmk9MWNNeHFyMVZneFdFJmQ9WVdrOVNqVm9hSGQ2TXpZbWNHbzlNVEU0TURVM09UYzJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wYQ--",
                        "9e1bb2700b79696770c9c931b182bf12260eb4e6");



        oauthConnection.initService(info);

        yqlQueryUtil.init(info);


        String requestUrl = oauthConnection.retrieveAuthorizationUrl();

        try
        {
            if(!oauthConnection.connect())
            {
                URI uri = new java.net.URI(requestUrl);
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri);

                System.out.println("Please type in verifier code:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String verifier = br.readLine();
                oauthConnection.retrieveAccessToken(verifier);
            }

        }
        catch (Exception e)
        {
            System.out.println("Problem with getting accessing url.");
        }

    }
}
