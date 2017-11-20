package com.booking.util;

import com.booking.booking_web_service.User;

import java.time.format.DateTimeFormatter;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/20/2017.
 */
public class PojoToWsModelConversionUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static User convertUser(com.booking.beans.models.User userToConvert) {
        User result = new User();
        result.setId(userToConvert.getId());
        result.setName(userToConvert.getName());
        result.setBirthday(userToConvert.getBirthday().format(DATE_FORMATTER));
        result.setRoles(userToConvert.getRoles());
        return result;
    }

}
