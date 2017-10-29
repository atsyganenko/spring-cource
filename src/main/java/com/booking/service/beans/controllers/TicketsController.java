package com.booking.service.beans.controllers;

import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.services.BookingService;
import com.booking.service.beans.view.TicketsPdfView;
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

    @Autowired
    public TicketsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/booked")
    public ModelAndView getBookTicketInPdf(@RequestParam String userEmail, Map<String, List<Ticket>> model) {
        model.put("tickets", bookingService.getTicketsByUser(userEmail));
        return new ModelAndView(new TicketsPdfView(), model);
    }

/*    double getTicketPrice(String event, String auditorium, LocalDateTime dateTime, List<Integer> seats, User user);

    Ticket bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(String event, String auditorium, LocalDateTime date);*/
}
