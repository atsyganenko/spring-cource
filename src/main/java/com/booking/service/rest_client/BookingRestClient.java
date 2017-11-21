package com.booking.service.rest_client;

import com.booking.service.beans.models.UserAccount;
import org.springframework.web.client.RestTemplate;

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
        return restTemplate.getForEntity(baseUrl + "/rest/get/account", UserAccount.class).getBody();
    }

}
