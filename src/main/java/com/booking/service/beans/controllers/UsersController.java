package com.booking.service.beans.controllers;

import com.booking.service.beans.models.User;
import com.booking.service.beans.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @RequestMapping("upload")
    String uploadUsers() {
        return "usersUploadForm";
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    String uploadUsers(@RequestParam MultipartFile[] files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(files[0].getBytes(), new TypeReference<List<User>>() {});
        users.forEach(userService::register);
        return "usersUploadForm";
    }

/*

    User register(User user);

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    List<Ticket> getBookedTickets();

*/
}


