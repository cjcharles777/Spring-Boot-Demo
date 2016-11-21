package com.donkeigy.services;

import com.yahoo.engine.YahooFantasyEngine;
import com.yahoo.objects.api.YahooApiInfo;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.utils.oauth.OAuthConnection;
import com.yahoo.utils.yql.YQLQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by cedric on 8/25/15.
 */

@Service("yahooDataService")
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
    public boolean isConnected()
    {
        return oauthConnection.connect();
    }

    public String retrieveAuthUrl()
    {
        return oauthConnection.retrieveAuthorizationUrl();
    }

    public boolean storeVerifierCode(String verifier)
    {
       return oauthConnection.retrieveAccessToken(verifier);
    }

    @PostConstruct
    public void init()
    {
        YahooApiInfo info =
                new YahooApiInfo("dj0yJmk9Y1hqTFRRVFBhU1pJJmQ9WVdrOU1Vb3dSRTl4TkdrbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD0yMg--",
                        "fee9549c91e87b35d3d788378acbfdab5498babb");

        oauthConnection.initService(info);
        yqlQueryUtil.init(info);


    }

    public YahooServiceFactory getFactory() {
        return factory;
    }

    public void setFactory(YahooServiceFactory factory) {
        this.factory = factory;
    }
}
