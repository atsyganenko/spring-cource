package com.booking.service.exceptions;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
public class TicketBookingException extends RuntimeException {

    public static final String NON_ENOUGH_MONEY = "Not enough money on account";
    public static final String NEGATIVE_AMOUNT = "Attempt to withdraw negative amount";

    public TicketBookingException(String message) {
        super(message);
    }

}