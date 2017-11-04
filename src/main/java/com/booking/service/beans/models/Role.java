package com.booking.service.beans.models;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */
public class Role {

    public static final Role REGISTERED_USER = new Role("REGISTERED_USER");
    public static final Role BOOKING_MANAGER = new Role("BOOKING_MANAGER");

    private long id;
    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
