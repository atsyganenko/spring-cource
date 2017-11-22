package com.booking.service.rest_client;

import com.booking.service.beans.models.Auditorium;
import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.UserAccount;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/21/2017.
 */

public class BookingRestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public BookingRestClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    public UserAccount getAccount() {
        return restTemplate.getForEntity(baseUrl + "/rest/account", UserAccount.class).getBody();
    }

    public UserAccount topUpAccount(long amount) {
        HttpEntity<Long> request = new HttpEntity<>(amount);
        return restTemplate.postForEntity(baseUrl + "/rest/account", request, UserAccount.class).getBody();
    }

    public List<Ticket> getBookedTicketsForEvent(long eventId) {
        ResponseEntity<List<Ticket>> responseEntity = restTemplate.exchange(baseUrl + "/rest/tickets/" + eventId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {
        });
        return responseEntity.getBody();
    }

    public List<Ticket> getBookedTicketsByUser() {
        ResponseEntity<List<Ticket>> responseEntity = restTemplate.exchange(baseUrl + "/rest/tickets/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {
        });
        return responseEntity.getBody();
    }

    public Ticket bookTicket(Ticket ticket) {
        HttpEntity<Ticket> request = new HttpEntity<>(ticket);
        return restTemplate.postForEntity(baseUrl + "/rest/tickets", request, Ticket.class).getBody();
    }

    public List<Event> getAllEvents() {
        ResponseEntity<List<Event>> responseEntity = restTemplate.exchange(baseUrl + "/rest/events", HttpMethod.GET, null, new ParameterizedTypeReference<List<Event>>() {
        });
        return responseEntity.getBody();
    }

    public Event getEventById(long id) {
        return restTemplate.getForEntity(baseUrl + "/rest/events/" + id, Event.class).getBody();
    }

    public Event createEvent(Event event) {
        HttpEntity<Event> request = new HttpEntity<>(event);
        return restTemplate.postForEntity(baseUrl + "/rest/events", request, Event.class).getBody();
    }

    public void deleteEvent(long eventId) {
        restTemplate.delete(baseUrl + "/rest/events/" + eventId);
    }

    public Auditorium getAuditorium(String name) {
        return restTemplate.getForEntity(baseUrl + "/rest/auditoriums/" + name, Auditorium.class).getBody();
    }

}
