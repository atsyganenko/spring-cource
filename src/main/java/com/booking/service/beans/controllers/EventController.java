package com.booking.service.beans.controllers;

import com.booking.service.beans.models.Auditorium;
import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Rate;
import com.booking.service.beans.models.UserRole;
import com.booking.service.beans.services.EventService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/26/2017.
 */

@Controller
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping("upload")
    String uploadEvents(Map<String, String> model) {
        model.put("formAction", "/events/upload");
        return "uploadForm";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    String uploadEvents(@RequestParam MultipartFile[] files) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Event event = new Event(1, "Test event", Rate.HIGH, 124.0, java.time.LocalDateTime.of(2016, 2, 6, 14, 45, 0),
                new Auditorium(1, "Test auditorium", 15, Arrays.asList(1, 2, 3, 4, 5)));
        List<Event> events = mapper.readValue(files[0].getBytes(), new TypeReference<List<Event>>() {
        });
        events.forEach(eventService::create);
        return "redirect:all";
    }

    @RequestMapping("all")
    String allEvents(Map<String, Object> model, HttpServletRequest request,
                     @ModelAttribute("bookingErrorMsg") String bookingErrorMsg) {
        if (request.isUserInRole(UserRole.BOOKING_MANAGER.name())) {
            model.put("isBookingManager", true);
        }
        model.put("events", eventService.getAll());
        return "eventsTable";
    }

    @RequestMapping("get")
    String getEvents(@RequestParam String name, Map<String, List<Event>> model) {
        model.put("events", eventService.getByName(name));
        return "eventsTable";
    }

    @RequestMapping("upcoming")
    String getUpcomingEvents(Map<String, List<Event>> model) {
        model.put("events", eventService.getNextEvents(LocalDateTime.now()));
        return "eventsTable";
    }

}

