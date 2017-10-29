package com.booking.service.beans.services;

import com.booking.service.beans.models.Auditorium;
import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/3/2016
 * Time: 11:02 AM
 */
public interface EventService {

    public Event create(Event event);

    public void remove(Event event);

    Event getEvent(String name, Auditorium auditorium, LocalDateTime dateTime);

    public List<Event> getByName(String name);

    Event getById(long id);

    public List<Event> getAll();

    public List<Event> getForDateRange(LocalDateTime from, LocalDateTime to);

    public List<Event> getNextEvents(LocalDateTime to);

    public Event assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date);
}
