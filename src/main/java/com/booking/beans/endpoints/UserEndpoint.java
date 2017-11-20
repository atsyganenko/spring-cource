package com.booking.beans.endpoints;

import com.booking.beans.services.UserService;
import com.booking.booking_web_service.GetUserRequest;
import com.booking.booking_web_service.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.booking.util.PojoToWsModelConversionUtil.convertUser;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/19/2017.
 */

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://booking.com/booking-web-service";

    private UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();

        response.setUser(convertUser(userService.getUserByEmail(request.getUserEmail())));
        return response;
    }
}
