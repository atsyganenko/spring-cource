package com.booking.service.beans.controllers;

import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.User;
import com.booking.service.beans.services.BookingFacade;
import com.booking.service.beans.views.TicketsPdfView;
import com.booking.service.exceptions.AccountOperationsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static com.booking.service.exceptions.AccountOperationsException.*;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/26/2017.
 */

@Controller
@RequestMapping
public class BookingController {

    private final TicketsPdfView ticketsPDFView;
    private final BookingFacade bookingFacade;

    @Autowired
    public BookingController(TicketsPdfView ticketsPDFView, BookingFacade bookingFacade) {
        this.ticketsPDFView = ticketsPDFView;
        this.bookingFacade = bookingFacade;
    }

    @RequestMapping("/balance/topUp")
    public String topUpAccount(@ModelAttribute("amount") double amount) {
        try {
            bookingFacade.topUpAccount(amount);
        } catch (ConcurrencyFailureException e) {
            throw new AccountOperationsException(TOO_MANY_SIMULTANEOUS_OPERATIONS);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/tickets/my", method = RequestMethod.GET)
    public String getBookedTicketByUser(Map<String, List<Ticket>> model) {
        model.put("tickets", bookingFacade.getBookedTickets());
        return "ticketsTable";
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public String getBookedTicketByEvent(@ModelAttribute("eventId") long eventId, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingFacade.getBookedTicketsForEvent(eventId));
        return "ticketsTable";
    }

    @Secured("ROLE_BOOKING_MANAGER")
    @RequestMapping(value = "/tickets", method = RequestMethod.POST, headers = "Accept=application/pdf")
    public ModelAndView getBookedTicketByEventInPdf(@ModelAttribute("eventId") long eventId, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingFacade.getBookedTicketsForEvent(eventId));
        return new ModelAndView(ticketsPDFView, model);
    }

    @RequestMapping(value = "/ticket/book", method = RequestMethod.POST)
    public String bookTicket(@ModelAttribute("eventId") long eventId, @ModelAttribute("seats") String seats) {
        try {
            bookingFacade.bookTicketForEvent(eventId, seats);
        } catch (ConcurrencyFailureException e) {
            throw new AccountOperationsException(TOO_MANY_SIMULTANEOUS_OPERATIONS);
        }
        return "redirect:/tickets/my";
    }

    @RequestMapping("login")
    public String logIn() {
        return "login";
    }

    @RequestMapping("/**")
    public String redirectToDefaultPage() {
        return "redirect:/home";
    }

    @RequestMapping("home")
    public String home(Map<String, User> model) {
        model.put("user", bookingFacade.getCurrentUser());
        return "index";
    }
}
