package com.donkeigy.controllers;

import com.donkeigy.objects.OAuthVerifyToken;
import com.donkeigy.services.YahooDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by cedric on 8/24/15.
 */
@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    YahooDataService yahooDataService;


    @RequestMapping(value="/verify",method=RequestMethod.POST)
    public boolean verify(@RequestBody final OAuthVerifyToken request)
    {
        //Store the token
        return yahooDataService.storeVerifierCode(request.getToken());
        //System.out.print(request.getToken());

    }

}
