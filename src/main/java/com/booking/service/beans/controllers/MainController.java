package com.booking.service.beans.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(final HttpServletRequest request, final HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:login";
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
