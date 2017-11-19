package com.booking.beans.controllers;

import com.booking.beans.models.Auditorium;
import com.booking.beans.services.AuditoriumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/26/2017.
 */

@Controller
@RequestMapping("auditoriums")
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }


    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String getAllAuditoriums(Map<String, List<Auditorium>> model) {
        model.put("auditoriums", auditoriumService.getAuditoriums());
        return "auditoriumsTable";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getAuditoriumByID(@PathVariable String name, Map<String, List<Auditorium>> model) {
        Auditorium auditorium = auditoriumService.getByName(name);
        model.put("auditoriums", auditorium != null ? Collections.singletonList(auditorium) : Collections.EMPTY_LIST);
        return "auditoriumsTable";
    }

    @RequestMapping(value = "{auditoriumName}/seats", method = RequestMethod.GET)
    @ResponseBody
    public Integer getSeatsNumber(@PathVariable String auditoriumName, HttpServletResponse response) {
        response.setContentType("text/plain");
        return auditoriumService.getSeatsNumber(auditoriumName);
    }

    @RequestMapping(value = "{auditoriumName}/seats/vip", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getVipSeats(@PathVariable String auditoriumName, HttpServletResponse response) {
        response.setContentType("text/plain");
        return auditoriumService.getVipSeats(auditoriumName);
    }

}



