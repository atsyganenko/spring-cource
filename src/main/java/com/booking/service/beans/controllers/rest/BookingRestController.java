package com.booking.service.beans.controllers.rest;

import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.UserAccount;
import com.booking.service.beans.services.BookingFacade;
import com.booking.service.beans.services.EventService;
import com.booking.service.exceptions.AccountOperationsException;
import com.booking.service.exceptions.TicketBookingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "topUpAccount/{amount}", method = RequestMethod.GET)
    public ResponseEntity<String> topUpAccount(@PathVariable long amount) {
        try {
            bookingFacade.topUpAccount(amount);
        } catch (AccountOperationsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request Failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @RequestMapping(value = "get/account", method = RequestMethod.GET)
    public UserAccount getBalance() {
        return bookingFacade.getUserAccount();
    }

    @RequestMapping(value = "get/tickets", method = RequestMethod.GET)
    public List<Ticket> getBookedTicketByUser() {
        return bookingFacade.getBookedTickets();
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "get/tickets/{eventId}", method = RequestMethod.GET)
    public List<Ticket> getBookedTicketByUser(@PathVariable long eventId) {
        return bookingFacade.getBookedTicketsForEvent(eventId);
    }

    @RequestMapping(value = "bookTicket/{eventId}/{seat}/", method = RequestMethod.GET)
    public ResponseEntity bookTicket(@PathVariable String eventId, @PathVariable String seat) {
        try {
            bookingFacade.bookTicketForEvent(eventId, seat);
        } catch (AccountOperationsException | TicketBookingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request Failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @RequestMapping(value = "get/events", method = RequestMethod.GET)
    public List<Event> getEvents() {
        return eventService.getAll();
    }

    @RequestMapping(value = "get/events/{eventName}", method = RequestMethod.GET)
    public List<Event> getEventsByName(@PathVariable String eventName) {
        return eventService.getByName(eventName);
    }

    @RequestMapping(value = "get/event/{eventId}", method = RequestMethod.GET)
    public Event getEventsByName(@PathVariable long eventId) {
        return eventService.getById(eventId);
    }
}
