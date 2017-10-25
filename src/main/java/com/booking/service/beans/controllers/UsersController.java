package com.booking.service.beans.controllers;

import com.booking.service.beans.models.User;
import com.booking.service.beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/25/2017.
 */
@Controller
@RequestMapping("users")
public class UsersController {

    private final UserService userService;

    @Autowired
    UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String showAddUserForm(Map<String, Object> model) {
        model.put("user", new User());
        return "userForm";
    }

}


