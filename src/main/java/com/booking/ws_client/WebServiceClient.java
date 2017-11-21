package com.booking.ws_client;

import com.booking.booking_web_service.GetUserRequest;
import com.booking.booking_web_service.GetUserResponse;
import com.booking.booking_web_service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/20/2017.
 */
@Component
public class WebServiceClient {

    private WebServiceTemplate webServiceTemplate;

    @Autowired
    WebServiceClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public User getUser(String email) {
        GetUserRequest userRequest = new GetUserRequest();
        userRequest.setUserEmail(email);
        GetUserResponse userResponse = (GetUserResponse) webServiceTemplate.marshalSendAndReceive(userRequest);
        return userResponse.getUser();
    }

}
