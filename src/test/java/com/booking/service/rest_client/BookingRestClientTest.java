package com.booking.service.rest_client;

import com.booking.service.Application;
import com.booking.service.beans.configuration.*;
import com.booking.service.beans.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/21/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class, WebMvcConfiguration.class, WebSecurityConfig.class, AuditoriumConfiguration.class, StrategiesConfiguration.class, MainConfiguration.class})
@TestPropertySource(properties = {"rest.user.login=admin@booking.com", "rest.user.password=admin123"})
public class BookingRestClientTest {

    @LocalServerPort
    private long port;

    @Autowired
    RestTemplate restTemplate;
    private BookingRestClient bookingRestClient;

    @Before
    public void setUp() {

        bookingRestClient = new BookingRestClient(String.format("http://localhost:%s", port), restTemplate);
    }

    @Test
    public void should_get_Account() {
        UserAccount account = bookingRestClient.getAccount();
        assertThat(account, notNullValue());
    }

    @Test
    public void should_topUp_account() {
        double startBalance = bookingRestClient.getAccount().getBalance();
        bookingRestClient.topUpAccount(100);
        double actualBalance = bookingRestClient.getAccount().getBalance();
        assertThat(actualBalance, is(startBalance + 100));
    }

    @Test
    public void should_createEvent() {
        Auditorium auditorium = bookingRestClient.getAuditorium("Blue hall");
        Event newEvent = new TestEventBuilder(auditorium).build();

        long eventId = bookingRestClient.createEvent(newEvent).getId();
        Event actual = bookingRestClient.getEventById(eventId);
        assertThat(actual.getName(), is(newEvent.getName()));
    }

    @Test
    public void should_deleteEvent() {
        Auditorium auditorium = bookingRestClient.getAuditorium("Blue hall");
        Event newEvent = new TestEventBuilder(auditorium).build();

        long eventId = bookingRestClient.createEvent(newEvent).getId();
        assertThat(bookingRestClient.getEventById(eventId), notNullValue());

        bookingRestClient.deleteEvent(eventId);
        assertThat(bookingRestClient.getEventById(eventId), nullValue());
    }

    @Test
    public void should_get_allEvents() {
        int initialEventCount = bookingRestClient.getAllEvents().size();

        Auditorium auditorium = bookingRestClient.getAuditorium("Blue hall");
        bookingRestClient.createEvent(new TestEventBuilder(auditorium).build()).getId();
        bookingRestClient.createEvent(new TestEventBuilder(auditorium).with(Event::setName, "Salsa Party").build()).getId();

        assertThat(bookingRestClient.getAllEvents().size(), is(initialEventCount + 2));
    }

    @Test
    public void should_bookTicket() {
        Auditorium auditorium = bookingRestClient.getAuditorium("Blue hall");
        Event event = bookingRestClient.createEvent(new TestEventBuilder(auditorium).with(Event::setBasePrice, 0d).build());

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setSeats("12,45");

        bookingRestClient.bookTicket(ticket);

        assertThat(bookingRestClient.getBookedTicketsForEvent(event.getId()), notNullValue());
    }

    @Test
    public void should_receive_Pdf() {
        ResponseEntity responseEntity = bookingRestClient.getBookedTicketsByUserInPdf();
        assertThat(responseEntity.getHeaders().getContentType(), is(MediaType.APPLICATION_PDF));
    }


    private class TestEventBuilder {
        private final Event event;

        TestEventBuilder(Auditorium auditorium) {
            this.event = new Event();
            event.setName("Horse Race");
            event.setBasePrice(500d);
            event.setRate(Rate.MID);
            event.setDateTime(LocalDateTime.now());
            event.setAuditorium(auditorium);
        }

        <T> TestEventBuilder with(BiConsumer<Event, T> setter, T value) {
            setter.accept(event, value);
            return this;
        }

        Event build() {
            return this.event;
        }
    }

}