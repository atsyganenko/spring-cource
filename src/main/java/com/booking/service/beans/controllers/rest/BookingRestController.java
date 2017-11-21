package com.booking.service.beans.controllers.rest;

import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.UserAccount;
import com.booking.service.beans.services.BookingFacade;
import com.booking.service.beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/21/2017.
 */

@RestController
@RequestMapping("rest")
public class BookingRestController {

    private final BookingFacade bookingFacade;
    private final EventService eventService;

    @Autowired
    public BookingRestController(BookingFacade bookingFacade, EventService eventService) {
        this.bookingFacade = bookingFacade;
        this.eventService = eventService;
    }

    @RequestMapping(value = "account", method = RequestMethod.POST)
    public UserAccount topUpAccount(@RequestBody long amount) {
        bookingFacade.topUpAccount(amount);
        return bookingFacade.getUserAccount();
    }

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public UserAccount getAccount() {
        return bookingFacade.getUserAccount();
    }

    @RequestMapping(value = "tickets", method = RequestMethod.GET)
    public List<Ticket> getBookedTicketsByUser() {
        return bookingFacade.getBookedTickets();
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "tickets/{eventId}", method = RequestMethod.GET)
    public List<Ticket> getBookedTicketsByUser(@PathVariable long eventId) {
        return bookingFacade.getBookedTicketsForEvent(eventId);
    }

    @RequestMapping(value = "tickets", method = RequestMethod.POST)
    public Ticket bookTicket(@RequestBody Ticket ticket) {
        return bookingFacade.bookTicketForEvent(Long.toString(ticket.getEvent().getId()), ticket.getSeats());
    }

    @RequestMapping(value = "events", method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }

    @RequestMapping(value = "events/{name}", method = RequestMethod.GET)
    public List<Event> getEventsByName(@PathVariable String name) {
        return eventService.getByName(name);
    }

    @RequestMapping(value = "event/{id}", method = RequestMethod.GET)
    public Event getEventsById(@PathVariable long id) {
        return eventService.getById(id);
    }

    @RequestMapping(value = "event/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEvent(@PathVariable long id) {
        eventService.remove(eventService.getById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "event", method = RequestMethod.DELETE)
    public Event createEvent(@RequestBody Event event) {
        return eventService.create(event);
    }
}
