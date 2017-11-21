package com.booking.beans.endpoints;

import com.booking.beans.services.UserService;
import com.booking.booking_web_service.*;
import com.booking.util.WSModelsConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

import static com.booking.util.WSModelsConversionUtil.convertToUser;
import static com.booking.util.WSModelsConversionUtil.convertToWsUser;

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


    /**
     * @return user identified by userEmail or all users if no email provided
     ***/
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        String userEmail = request.getUserEmail();
        if (userEmail != null && !userEmail.isEmpty()) {
            response.getUser().add(convertToWsUser(userService.getUserByEmail(request.getUserEmail())));
        } else {
            response.getUser().addAll(userService.getAll().stream().map(WSModelsConversionUtil::convertToWsUser).collect(Collectors.toList()));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
        DeleteUserResponse response = new DeleteUserResponse();
        try {
            userService.remove(userService.getUserByEmail(request.getUserEmail()));
            response.setSuccess(true);
        } catch (RuntimeException ex) {
            response.setSuccess(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        try {
            userService.register(convertToUser(request.getUser()));
            response.setSuccess(true);
        } catch (RuntimeException ex) {
            response.setSuccess(false);
        }
        return response;
    }
}
