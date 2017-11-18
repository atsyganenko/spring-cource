package com.booking.service.exceptions;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/18/2017.
 */
public class TicketBookingException extends RuntimeException {
    private static final String NO_EVENT_FOUND_TEMPLATE = "No event with id=%s found";

    public TicketBookingException(String message) {
        super(message);
    }

    public static TicketBookingException newEventNotFoundException(String eventId) {
        return new TicketBookingException(String.format(NO_EVENT_FOUND_TEMPLATE, eventId));
    }
}
