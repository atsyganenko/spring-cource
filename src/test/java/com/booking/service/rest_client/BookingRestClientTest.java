package com.booking.service.rest_client;

import com.booking.service.Application;
import com.booking.service.beans.configuration.*;
import com.booking.service.beans.models.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.notNullValue;
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

}