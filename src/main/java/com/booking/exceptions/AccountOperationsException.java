package com.booking.exceptions;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
public class AccountOperationsException extends RuntimeException {

    public static final String NON_ENOUGH_MONEY = "Not enough money on account";
    public static final String NEGATIVE_AMOUNT_WITHDRAW = "Attempt to withdraw negative amount";
    public static final String NEGATIVE_AMOUNT_TOP_UP = "Attempt to top up balance with the negative amount";
    public static final String TOO_MANY_SIMULTANEOUS_OPERATIONS = "Too may simultaneous operations from one account";

    public AccountOperationsException(String message) {
        super(message);
    }

}