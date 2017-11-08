package com.booking.service.beans.services;

import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.User;
import com.booking.service.beans.models.UserAccount;
import com.booking.service.beans.views.TicketsPdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/8/2017.
 */

@Service
@Transactional
public class BookingFacade {

    private final TicketsService ticketsService;
    private final UserService userService;
    private final EventService eventService;
    private final UserAccountService userAccountService;


    @Autowired
    public BookingFacade(TicketsService ticketsService, UserService userService,
                         EventService eventService, UserAccountService userAccountService) {
        this.ticketsService = ticketsService;
        this.userService = userService;
        this.eventService = eventService;
        this.userAccountService = userAccountService;
    }

    public void topUpAccount (double amount) {
        userAccountService.topUpAccount(getCurrentUserFromSecurityContext().getAccount(), amount);
    }

    public void bookTicketForEvent(long eventId, String seats) {
        Ticket ticket = new Ticket();
        User user = getCurrentUserFromSecurityContext();
        Event event = eventService.getById(eventId);
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setDateTime(LocalDateTime.now());
        ticket.setSeats(seats);
        ticket.setPrice(getTicketPrice(ticket));
        userAccountService.withdrawMoney(user.getAccount(), ticket.getPrice());
        ticketsService.bookTicket(user, ticket);
    }

    public List<Ticket> getBookedTickets() {
        User user = getCurrentUserFromSecurityContext();
        return ticketsService.getTicketsByUser(user);
    }

    public List<Ticket> getBookedTicketsForEvent(long eventId) {
        return ticketsService.getTicketsForEvent(eventId);
    }

    public User getCurrentUserFromSecurityContext() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByEmail(userEmail);
    }

    private double getTicketPrice(Ticket ticket) {
        Event event = ticket.getEvent();
        User user = ticket.getUser();
        return ticketsService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                event.getDateTime(), ticket.getSeatsList(), user);
    }
}
