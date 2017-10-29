package com.booking.service.beans.controllers;

import com.booking.service.beans.models.User;
import com.booking.service.beans.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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

    @RequestMapping("all")
    String allEvents(Map<String, List<User>> model) {
        model.put("users", userService.getAll());
        return "usersTable";
    }

    @RequestMapping("upload")
    String uploadUsers(Map<String, String> model) {
        model.put("formAction", "/users/upload");
        return "uploadForm";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    String uploadUsers(@RequestParam MultipartFile[] files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<User> users = mapper.readValue(files[0].getBytes(), new TypeReference<List<User>>() {
        });
        users.forEach(userService::register);
        return "redirect:all";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    String removeUser(@PathVariable String id) {
        User user = userService.getById(Long.parseLong(id));
        userService.remove(user);
        return "redirect:all";
    }

    @RequestMapping(value = "/get", params = "email", method = RequestMethod.GET)
    String getUserByEmail(@RequestParam String email, Map<String, List<User>> model) {
        User user = userService.getUserByEmail(email);
        model.put("users", user != null ? Collections.singletonList(user) : Collections.EMPTY_LIST);
        return "usersTable";
    }

    @RequestMapping(value = "/get", params = "name", method = RequestMethod.GET)
    String getUserByName(@RequestParam String name, Map<String, List<User>> model) {
        model.put("users", userService.getUsersByName(name));
        return "usersTable";
    }

}


