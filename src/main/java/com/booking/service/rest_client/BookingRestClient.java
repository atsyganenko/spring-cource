package com.booking.service.rest_client;

import com.booking.service.beans.models.Auditorium;
import com.booking.service.beans.models.Event;
import com.booking.service.beans.models.Ticket;
import com.booking.service.beans.models.UserAccount;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/21/2017.
 */

public class BookingRestClient {

    private static final String ACCOUNT_RELATIVE_URL = "/rest/account";
    private static final String TICKETS_RELATIVE_URL = "/rest/tickets";
    private static final String EVENTS_RELATIVE_URL = "/rest/events";
    private static final String AUDITORIUMS_RELATIVE_URL = "/rest/auditoriums";

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public BookingRestClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    public UserAccount getAccount() {
        return getForEntity(UserAccount.class, ACCOUNT_RELATIVE_URL);
    }

    public UserAccount topUpAccount(long amount) {
        return postForEntity(UserAccount.class, amount, ACCOUNT_RELATIVE_URL);
    }

    public List<Ticket> getBookedTicketsForEvent(long eventId) {
        return getForListOfEntities(Ticket.class, TICKETS_RELATIVE_URL, Long.toString(eventId));
    }

    public ResponseEntity getBookedTicketsByUserInPdf() {
        return getBookedTicketsInPdf();
    }

    public ResponseEntity getBookedTicketsForEventInPdf(long eventId) {
        return getBookedTicketsInPdf(Long.toString(eventId));
    }

    public List<Ticket> getBookedTicketsByUser() {
        return getForListOfEntities(Ticket.class, TICKETS_RELATIVE_URL);
    }

    public Ticket bookTicket(Ticket ticket) {
        return postForEntity(Ticket.class, ticket, TICKETS_RELATIVE_URL);
    }

    public List<Event> getAllEvents() {
        return getForListOfEntities(Event.class, EVENTS_RELATIVE_URL);
    }

    public Event getEventById(long eventId) {
        return getForEntity(Event.class, EVENTS_RELATIVE_URL, Long.toString(eventId));
    }

    public Event createEvent(Event event) {
        return postForEntity(Event.class, event, EVENTS_RELATIVE_URL);
    }

    public void deleteEvent(long eventId) {
        restTemplate.delete(buildUrl(EVENTS_RELATIVE_URL, Long.toString(eventId)));
    }

    public Auditorium getAuditorium(String name) {
        return getForEntity(Auditorium.class, AUDITORIUMS_RELATIVE_URL, name);
    }

    private ResponseEntity<byte[]> getBookedTicketsInPdf(String... urlParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_PDF));
        String url = buildUrl(TICKETS_RELATIVE_URL, urlParams);
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("params", headers), byte[].class);
    }

    private <T> T getForEntity(Class<T> responseType, String relativeUrl, String... pathParams) {
        return restTemplate.getForEntity(buildUrl(relativeUrl, pathParams), responseType).getBody();
    }

    private <T> List<T> getForListOfEntities(Class<T> responseType, String relativeUrl, String... pathParams) {
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(buildUrl(relativeUrl, pathParams), HttpMethod.GET, null, new ParameterizedTypeReference<List<T>>() {
        });
        return responseEntity.getBody();
    }

    private <B, T> T postForEntity(Class<T> responseType, B requestBody, String relativeUrl, String... pathParams) {
        HttpEntity<B> request = new HttpEntity<>(requestBody);
        return restTemplate.postForEntity(buildUrl(relativeUrl, pathParams), request, responseType).getBody();
    }

    private String buildUrl(String relativeUrl, String... pathParams) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append(relativeUrl);
        Arrays.stream(pathParams).forEach(param -> urlBuilder.append("/").append(param));
        return urlBuilder.toString();
    }
}
