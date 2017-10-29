package com.booking.service.beans.controllers;

import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.services.BookingService;
import com.booking.service.beans.views.TicketsPdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    public TicketsController(BookingService bookingService, TicketsPdfView ticketsPDFView) {
        this.bookingService = bookingService;
        this.ticketsPDFView = ticketsPDFView;
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

}
