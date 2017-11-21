package com.booking.beans.endpoints;

import com.booking.beans.services.EventService;
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
 * on 11/21/2017.
 */

@Endpoint
public class EventEndpoint {

    private static final String NAMESPACE_URI = "http://booking.com/booking-web-service";
    private final EventService eventService;

    @Autowired
    public EventEndpoint(EventService eventService) {
        this.eventService = eventService;
    }


    /**
     * @return event identified by id or all events if no email provided
     ***/
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateEventResponse createEvent(@RequestPayload CreateEventRequest request) {
        CreateEventResponse response = new CreateEventResponse();
        try {
            eventService.create(convertToEvent(request.getEvent()));
            response.setSuccess(true);
        } catch (RuntimeException ex) {
            response.setSuccess(false);
        }
        return response;
    }
}
