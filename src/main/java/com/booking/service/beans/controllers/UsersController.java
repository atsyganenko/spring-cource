package com.booking.service.beans.controllers;

import com.booking.service.beans.models.User;
import com.booking.service.beans.models.UserRole;
import com.booking.service.beans.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("all")
    String allEvents(Map<String, List<User>> model) {
        model.put("users", userService.getAll());
        return "usersTable";
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    String uploadUsers(@RequestParam MultipartFile[] files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<User> users = mapper.readValue(files[0].getBytes(), new TypeReference<List<User>>() {
        });
        users.forEach(userService::register);
        return "redirect:all";
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    String addUsers(@ModelAttribute("name") String name,
                    @ModelAttribute("email") String email,
                    @ModelAttribute("birthday")
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
                    @ModelAttribute("password") String password,
                    @ModelAttribute("isBookingManager") String isBookingManager) {
        User user = new User(0, name, email, birthday);
        user.setEncryptedPassword(passwordEncoder.encode(password));
        if ("on".equals(isBookingManager)) {
            user.addRole(UserRole.BOOKING_MANAGER.name());
        }
        userService.register(user);
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


