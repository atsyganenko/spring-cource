package com.booking.service.beans.controllers;

import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.User;
import com.booking.service.beans.services.TicketsService;
import com.booking.service.beans.services.EventService;
import com.booking.service.beans.services.UserService;
import com.booking.service.beans.views.TicketsPdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/26/2017.
 */

@Controller
@RequestMapping("tickets")
public class TicketsController {

    private final TicketsService bookingService;
    private final TicketsPdfView ticketsPDFView;
    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public TicketsController(TicketsService bookingService, TicketsPdfView ticketsPDFView,
                             UserService userService, EventService eventService) {
        this.bookingService = bookingService;
        this.ticketsPDFView = ticketsPDFView;
        this.userService = userService;
        this.eventService = eventService;
    }

    @RequestMapping(value = "/booked", params = "userEmail", headers = "Accept=application/pdf")
    public ModelAndView getBookedTicketInPdfByUser(@RequestParam String userEmail, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsByUser(userEmail));
        return new ModelAndView(ticketsPDFView, model);
    }

    @RequestMapping(value = "/booked", params = "userEmail")
    public String getBookedTicketByUser(@RequestParam String userEmail, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsByUser(userEmail));
        return "ticketsTable";
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "/booked", params = "eventId", headers = "Accept=application/pdf")
    public ModelAndView getBookedInPdfTicketByEvent(@RequestParam long eventId, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsForEvent(eventId));
        return new ModelAndView(ticketsPDFView, model);
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "/booked", params = "eventId")
    public String getBookedTicketByEvent(@RequestParam long eventId, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsForEvent(eventId));
        return "ticketsTable";
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "/booked", method = RequestMethod.POST)
    public String getBookedTicket(@ModelAttribute("eventId") String eventId, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsForEvent(Long.parseLong(eventId)));
        return "ticketsTable";
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String bookTicket(@ModelAttribute("eventId") long eventId,
                             @ModelAttribute("userEmail") String userEmail, @ModelAttribute("seats") String seats) {

        Ticket ticket = new Ticket();
        User user = userService.getUserByEmail(userEmail);
        Event event = eventService.getById(eventId);
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setDateTime(LocalDateTime.now());
        ticket.setSeats(seats);
        ticket.setPrice(getTicketPrice(ticket));
        bookingService.bookTicket(user, ticket);
        return String.format("redirect:/tickets/booked?userEmail=%s", user.getEmail());
    }

    private double getTicketPrice(Ticket ticket) {
        Event event = ticket.getEvent();
        User user = ticket.getUser();
        return bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                event.getDateTime(), ticket.getSeatsList(), user);
    }
}
