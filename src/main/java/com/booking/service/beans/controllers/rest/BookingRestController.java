package com.booking.service.beans.controllers.rest;

import com.booking.service.beans.models.Auditorium;
import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.UserAccount;
import com.booking.service.beans.services.AuditoriumService;
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

    private static final String JSON = "application/json";
    private static final String PDF = "application/pdf";
    private final BookingFacade bookingFacade;
    private final EventService eventService;
    private final AuditoriumService auditoriumService;

    @Autowired
    public BookingRestController(BookingFacade bookingFacade, EventService eventService, AuditoriumService auditoriumService) {
        this.bookingFacade = bookingFacade;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
    }

    @RequestMapping(value = "account", method = RequestMethod.POST, produces = {JSON})
    public UserAccount topUpAccount(@RequestBody Long amount) {
        bookingFacade.topUpAccount(amount);
        return bookingFacade.getUserAccount();
    }

    @RequestMapping(value = "account", method = RequestMethod.GET, produces = {JSON})
    public UserAccount getAccount() {
        return bookingFacade.getUserAccount();
    }

    @RequestMapping(value = "tickets", method = RequestMethod.GET, produces = {JSON, PDF})
    public List<Ticket> getBookedTicketsByUser() {
        return bookingFacade.getBookedTickets();
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "tickets/{eventId}", method = RequestMethod.GET, produces = {JSON, PDF})
    public List<Ticket> getBookedTicketsForEvent(@PathVariable long eventId) {
        return bookingFacade.getBookedTicketsForEvent(eventId);
    }

    @RequestMapping(value = "tickets", method = RequestMethod.POST, produces = {JSON, PDF})
    public Ticket bookTicket(@RequestBody Ticket ticket) {
        return bookingFacade.bookTicketForEvent(Long.toString(ticket.getEvent().getId()), ticket.getSeats());
    }

    @RequestMapping(value = "events", method = RequestMethod.GET, produces = {JSON})
    public List<Event> getAllEvents() {
        return eventService.getAll();
    }

    @RequestMapping(value = "events/{id}", method = RequestMethod.GET, produces = {JSON})
    public Event getEventById(@PathVariable long id) {
        return eventService.getById(id);
    }

    @RequestMapping(value = "events/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEvent(@PathVariable long id) {
        eventService.remove(eventService.getById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "events", method = RequestMethod.POST)
    public Event createEvent(@RequestBody Event event) {
        return eventService.create(event);
    }

    @RequestMapping(value = "auditoriums/{name}", method = RequestMethod.GET)
    public Auditorium createEvent(@PathVariable String name) {
        return auditoriumService.getByName(name);
    }
}
