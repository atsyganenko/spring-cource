package com.booking.ws_client;

import com.booking.Application;
import com.booking.beans.configuration.AuditoriumConfiguration;
import com.booking.beans.configuration.StrategiesConfiguration;
import com.booking.beans.configuration.WebServiceConfiguration;
import com.booking.beans.models.UserRole;
import com.booking.booking_web_service.Auditorium;
import com.booking.booking_web_service.Event;
import com.booking.booking_web_service.Rate;
import com.booking.booking_web_service.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.booking.util.WSModelsConversionUtil.localDateTimeToStr;
import static com.booking.util.WSModelsConversionUtil.localDateToStr;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/20/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class, WsClientConfiguration.class, AuditoriumConfiguration.class, StrategiesConfiguration.class, WebServiceConfiguration.class})
public class BookingWebServiceClientTest {

    @Autowired
    private WebServiceTemplate webServiceTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private BookingWebServiceClient webServiceClient;

    @LocalServerPort
    private int port = 0;

    @Before
    public void setUp() {
        webServiceTemplate.setDefaultUri("http://localhost:" + port + "/ws");
        webServiceClient = new BookingWebServiceClient(webServiceTemplate);
    }

    @Test
    public void should_create_and_get_user() {
        User bob = createUser("Bob", "bob@test.com", true);
        webServiceClient.createUser(bob);
        Optional<User> actualUser = webServiceClient.getUser(bob.getEmail());
        validateUser(actualUser.get(), bob);
    }

    @Test
    public void should_delete_user() {
        User bob = createUser("Bob", "bob@test.com", true);
        webServiceClient.createUser(bob);
        boolean deleteSuccess = webServiceClient.deleteUser(bob.getEmail());
        Optional<User> actualUser = webServiceClient.getUser(bob.getEmail());
        assertThat(deleteSuccess, is(true));
        assertThat(actualUser.isPresent(), is(false));
    }

    @Test
    public void should_create_and_get_event() {
        Event event = createEvent("Moto race", LocalDateTime.now());
        long newEventId = webServiceClient.createEvent(event).get();
        Optional<Event> actualEvent = webServiceClient.getEvent(Long.toString(newEventId));
        validateEvent(actualEvent.get(), event);
    }

    @Test
    public void should_delete_event() {
        Event event = createEvent("Moto race", LocalDateTime.now());
        long newEventId = webServiceClient.createEvent(event).get();
        boolean deleteSuccess = webServiceClient.deleteEvent(Long.toString(newEventId));
        Optional<Event> actualEvent = webServiceClient.getEvent(Long.toString(newEventId));
        assertThat(deleteSuccess, is(true));
        assertThat(actualEvent.isPresent(), is(false));
    }

    private void validateEvent(Event actual, Event expected) {
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getPrice(), is(expected.getPrice()));
        assertThat(actual.getRate(), is(expected.getRate()));
        assertThat(actual.getDateTime(), is(expected.getDateTime()));
        assertThat(actual.getAuditorium().getName(), is(actual.getAuditorium().getName()));
    }

    private void validateUser(User actual, User expected) {
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getEmail(), is(expected.getEmail()));
        assertThat(actual.getEncryptedPassword(), is(actual.getEncryptedPassword()));
        assertThat(actual.getRoles().contains(expected.getRoles()), is(true));
        assertThat(actual.getBirthday(), is(expected.getBirthday()));
    }

    private User createUser(String name, String email, boolean isBookingManager) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        if (isBookingManager) {
            user.setRoles(UserRole.BOOKING_MANAGER.name());
        }
        user.setEncryptedPassword(passwordEncoder.encode("testPassword"));
        user.setBirthday(localDateToStr(LocalDate.now()));
        return user;
    }

    private Event createEvent(String name, LocalDateTime dateTime) {
        Event event = new Event();
        event.setId(0);
        event.setName(name);
        event.setPrice(500);
        event.setRate(Rate.HIGH);
        event.setDateTime(localDateTimeToStr(dateTime));
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Blue hall");
        event.setAuditorium(auditorium);
        return event;
    }

}