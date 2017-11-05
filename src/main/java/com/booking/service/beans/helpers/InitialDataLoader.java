package com.booking.service.beans.helpers;

import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Rate;
import com.booking.service.beans.models.Role;
import com.booking.service.beans.models.User;
import com.booking.service.beans.services.AuditoriumService;
import com.booking.service.beans.services.EventService;
import com.booking.service.beans.services.RoleService;
import com.booking.service.beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private AuditoriumService auditoriumService;
    private EventService eventService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUsers();
        loadEvents();
    }

    private void loadUsers() {
        roleService.createRole(Role.REGISTERED_USER);
        roleService.createRole(Role.BOOKING_MANAGER);

        User adminUser = new User("admin@booking.com", "admin", LocalDate.of(1993, 11, 14));
        adminUser.setEncryptedPassword(passwordEncoder.encode("admin123"));
        adminUser.setRoles(new HashSet<Role>(Arrays.asList(Role.REGISTERED_USER, Role.BOOKING_MANAGER)));

        User user = new User("user@booking.com", "user", LocalDate.of(1897, 12, 1));
        user.setEncryptedPassword(passwordEncoder.encode("user123"));
        user.setRoles(new HashSet<>(Collections.singletonList(Role.REGISTERED_USER)));
        userService.register(user);
        userService.register(adminUser);
    }

    private void loadEvents() {
        Event danceShow = new Event("Dance Show", Rate.HIGH, 300, LocalDateTime.of(2018, 1, 15, 15, 0), auditoriumService.getByName("Blue hall"));
        Event football = new Event("Football", Rate.MID, 250, LocalDateTime.of(2018, 2, 17, 21, 30), auditoriumService.getByName("Red hall"));
        eventService.create(danceShow);
        eventService.create(football);
    }

}