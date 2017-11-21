package com.booking.ws_client;

import com.booking.booking_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> getUser(String email) {
        GetUserRequest userRequest = new GetUserRequest();
        userRequest.setUserEmail(email);
        GetUserResponse userResponse = (GetUserResponse) webServiceTemplate.marshalSendAndReceive(userRequest);
        return userResponse.getUser().stream().findAny();
    }

    public List<User> getAllUsers() {
        GetUserRequest userRequest = new GetUserRequest();
        GetUserResponse userResponse = (GetUserResponse) webServiceTemplate.marshalSendAndReceive(userRequest);
        return userResponse.getUser();
    }

    public boolean deleteUser(String email) {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUserEmail(email);
        DeleteUserResponse response = (DeleteUserResponse) webServiceTemplate.marshalSendAndReceive(deleteUserRequest);
        return response.isSuccess();
    }

    public boolean createUser(User user) {
        CreateUserRequest deleteUserRequest = new CreateUserRequest();
        deleteUserRequest.setUser(user);
        DeleteUserResponse response = (DeleteUserResponse) webServiceTemplate.marshalSendAndReceive(deleteUserRequest);
        return response.isSuccess();
    }

}
