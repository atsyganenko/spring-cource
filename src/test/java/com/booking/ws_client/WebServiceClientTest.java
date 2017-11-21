package com.booking.ws_client;

import com.booking.Application;
import com.booking.beans.configuration.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/20/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class, WebServiceClientConfiguration.class, AuditoriumConfiguration.class, StrategiesConfiguration.class, WebServiceConfiguration.class,
        })
public class WebServiceClientTest {

    @Autowired
    WebServiceTemplate webServiceTemplate;
    private WebServiceClient webServiceClient;

    @LocalServerPort
    private int port = 0;

    @Before
    public void setUp() {
        webServiceTemplate.setDefaultUri("http://localhost:" + port + "/ws");
        webServiceClient = new WebServiceClient(webServiceTemplate);
    }

    @Test
    public void test() {
        webServiceClient.getUser("admin@booking.com");
    }


}