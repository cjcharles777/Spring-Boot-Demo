package com.donkeigy.controllers;

import com.donkeigy.services.YahooDataService;
import com.yahoo.objects.players.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cedric on 8/25/15.
 */
@Controller
public class IndexController
{

    @Autowired
    YahooDataService yahooDataService;

    private List<Player> players=new ArrayList<Player>();

    @RequestMapping(value="/")
    public String loadHomePage(Model model){

        yahooDataService.init();

        model.addAttribute("players", players);

        return "index";
    }


    @RequestMapping(value="/addPlayer",method= RequestMethod.POST)
    public String addPlayer(@ModelAttribute("player")Player player, Model model){

        players.add(player);

        return "redirect:/";
    }

}
