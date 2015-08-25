package com.donkeigy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cedric on 8/24/15.
 */
@Controller
public class JSPController {

    @RequestMapping("/jsptest")
    public String test(ModelAndView modelAndView) {

        return "jsp-spring-boot";
    }

}
