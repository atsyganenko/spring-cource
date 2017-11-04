package com.booking.service.beans.controllers;

import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Role;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.User;
import com.booking.service.beans.services.BookingService;
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

    private final BookingService bookingService;
    private final TicketsPdfView ticketsPDFView;
    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public TicketsController(BookingService bookingService, TicketsPdfView ticketsPDFView,
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

    @RequestMapping(value = "/booked", params = "eventId", headers = "Accept=application/pdf")
    public ModelAndView getBookedTicketByEvent(@RequestParam long eventId, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsForEvent(eventId));
        return new ModelAndView(ticketsPDFView, model);
    }

    @RequestMapping(value = "/booked", params = "eventId")
    public String getBookedTicketInPdfByEvent(@RequestParam long eventId, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsForEvent(eventId));
        return "ticketsTable";
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping("book")
    public String bookTicket(Map<String, Object> model, @RequestParam long eventId, @RequestParam String userEmail) {
        Ticket ticket = new Ticket();
        User user = userService.getUserByEmail(userEmail);
        Event event = eventService.getById(eventId);
        ticket.setUser(user);
        ticket.setEvent(event);
        model.put("ticket", ticket);
        return "bookTicketForm";
    }

    @RequestMapping(value = "book/confirm", method = RequestMethod.POST)
    public String bookTicket(@ModelAttribute("ticket") Ticket ticket) {
        Event event = eventService.getById(ticket.getEvent().getId());
        User user = userService.getUserByEmail(ticket.getUser().getEmail());
        double price = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), ticket.getSeatsList(), user);
        ticket.setPrice(price);
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setDateTime(LocalDateTime.now());
        bookingService.bookTicket(user, ticket);
        return String.format("redirect:/tickets/booked?userEmail=%s", user.getEmail());
    }

}
