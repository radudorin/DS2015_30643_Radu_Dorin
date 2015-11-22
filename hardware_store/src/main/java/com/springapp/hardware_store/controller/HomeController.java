package com.springapp.hardware_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by radud on 22/11/2015.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "Homepage", method = RequestMethod.GET)
    public String homepage() {
        return "Homepage";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        return "Homepage";
    }

}
