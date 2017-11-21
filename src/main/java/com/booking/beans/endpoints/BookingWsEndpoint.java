package com.booking.beans.endpoints;

import com.booking.beans.services.AuditoriumService;
import com.booking.beans.services.EventService;
import com.booking.beans.services.UserService;
import com.booking.booking_web_service.*;
import com.booking.util.WSModelsConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

import static com.booking.util.WSModelsConversionUtil.*;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/19/2017.
 */

@Endpoint
public class BookingWsEndpoint {

    private static final String NAMESPACE_URI = "http://booking.com/booking-web-service";

    private UserService userService;
    private final EventService eventService;
    private final AuditoriumService auditoriumService;

    @Autowired
    public BookingWsEndpoint(UserService userService, EventService eventService, AuditoriumService auditoriumService) {
        this.userService = userService;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
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
            response.getUser().add(convertToWsUser(userService.getUserByEmail(userEmail)));
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


    /**
     * @return event identified by id or all events if no email provided
     ***/
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventRequest")
    @ResponsePayload
    public GetEventResponse getEvent(@RequestPayload GetEventRequest request) {
        GetEventResponse response = new GetEventResponse();
        String eventId = request.getId();
        if (eventId != null && !eventId.isEmpty()) {
            response.getEvent().add(convertToWsEvent(eventService.getById(Long.parseLong(eventId))));
        } else {
            response.getEvent().addAll(eventService.getAll().stream().map(WSModelsConversionUtil::convertToWsEvent).collect(Collectors.toList()));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEventRequest")
    @ResponsePayload
    public DeleteEventResponse deleteEvent(@RequestPayload DeleteEventRequest request) {
        DeleteEventResponse response = new DeleteEventResponse();
        try {
            eventService.remove(eventService.getById(Long.parseLong(request.getId())));
            response.setSuccess(true);
        } catch (RuntimeException ex) {
            response.setSuccess(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEventRequest")
    @ResponsePayload
    public CreateEventResponse createEvent(@RequestPayload CreateEventRequest request) {
        CreateEventResponse response = new CreateEventResponse();
        try {
            com.booking.beans.models.Event convertedEvent = convertToEvent(request.getEvent());
            convertedEvent.setAuditorium(auditoriumService.getByName(convertedEvent.getAuditorium().getName()));
            com.booking.beans.models.Event newEvent = eventService.create(convertedEvent);
            response.setNewEventId(newEvent.getId());
            response.setSuccess(true);
        } catch (RuntimeException ex) {
            response.setSuccess(false);
        }
        return response;
    }
}
