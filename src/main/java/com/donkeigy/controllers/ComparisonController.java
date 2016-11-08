package com.donkeigy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yahoo.services.LeagueService;
import com.yahoo.services.YahooServiceFactory;
import com.yahoo.services.enums.ServiceType;

/**
 * Created by cedric on 11/8/16.
 */
@Controller
@RequestMapping("/comparison")
public class ComparisonController extends BaseController
{

    @RequestMapping(value="/")
    public String loadHomePage(Model model)
    {
        return super.loadHomePage(model, "comparison");
    }
}
