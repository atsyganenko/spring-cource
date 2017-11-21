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
public class BookingWebServiceClient {

    private WebServiceTemplate webServiceTemplate;

    @Autowired
    BookingWebServiceClient(WebServiceTemplate webServiceTemplate) {
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
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUser(user);
        CreateUserResponse response = (CreateUserResponse) webServiceTemplate.marshalSendAndReceive(createUserRequest);
        return response.isSuccess();
    }

    public Optional<Event> getEvent(String eventId) {
        GetEventRequest eventRequest = new GetEventRequest();
        eventRequest.setId(eventId);
        GetEventResponse eventResponse = (GetEventResponse) webServiceTemplate.marshalSendAndReceive(eventRequest);
        return eventResponse.getEvent().stream().findAny();
    }

    public List<Event> getAllEvents() {
        GetEventRequest eventRequest = new GetEventRequest();
        GetEventResponse eventResponse = (GetEventResponse) webServiceTemplate.marshalSendAndReceive(eventRequest);
        return eventResponse.getEvent();
    }

    public boolean deleteEvent(String eventId) {
        DeleteEventRequest deleteEventRequest = new DeleteEventRequest();
        deleteEventRequest.setId(eventId);
        DeleteEventResponse response = (DeleteEventResponse) webServiceTemplate.marshalSendAndReceive(deleteEventRequest);
        return response.isSuccess();
    }

    public boolean createEvent(Event event) {
        CreateEventRequest createEventRequest = new CreateEventRequest();
        createEventRequest.setEvent(event);
        CreateEventResponse response = (CreateEventResponse) webServiceTemplate.marshalSendAndReceive(createEventRequest);
        return response.isSuccess();
    }

}
