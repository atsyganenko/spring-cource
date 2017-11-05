package com.booking.service.beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */
@Controller
public class MainController {


    @RequestMapping("login")
    public String logIn() {
        return "login";
    }

    @RequestMapping("/**")
    public String redirectToDefaultPage() {
        return "redirect:/home";
    }

    @RequestMapping("home")
    public String home() {
        return "index";
    }

}
