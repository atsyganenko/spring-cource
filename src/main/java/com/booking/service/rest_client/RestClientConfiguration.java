package com.booking.service.rest_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/21/2017.
 */

@Configuration
public class RestClientConfiguration {

    @Value("${rest.user.login}")
    private String restUserLogin;

    @Value("${rest.user.password}")
    private String restUserPassword;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().basicAuthorization(restUserLogin, restUserPassword).build();
    }
}
