package com.donkeigy.services;

import com.yahoo.services.YahooServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by cedric on 9/15/15.
 */
public abstract class BaseService
{
    @Autowired
    YahooDataService yahooDataService;
    YahooServiceFactory factory;

    @PostConstruct
    protected void init()
    {
        factory = yahooDataService.getFactory();
        initService();

    }
    protected abstract void initService();
}
