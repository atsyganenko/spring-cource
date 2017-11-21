package com.booking.ws_client;

import com.booking.booking_web_service.GetUserRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/20/2017.
 */

@Configuration
public class WsClientConfiguration {


    @Bean
    WebServiceTemplate webServiceTemplate(Jaxb2Marshaller defaultMarshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://localhost:8080/ws");
        webServiceTemplate.setMarshaller(defaultMarshaller);
        webServiceTemplate.setUnmarshaller(defaultMarshaller);
        return webServiceTemplate;
    }

    @Bean
    Jaxb2Marshaller defaultMarshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(ClassUtils.getPackageName(GetUserRequest.class));
        return jaxb2Marshaller;
    }
}
