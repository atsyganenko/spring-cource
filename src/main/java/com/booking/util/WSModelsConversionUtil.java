package com.booking.util;

import com.booking.booking_web_service.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/20/2017.
 */
public class WSModelsConversionUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static User convertToWsUser(com.booking.beans.models.User userToConvert) {
        if(userToConvert == null) {
            return  null;
        }
        User result = new User();
        result.setId(userToConvert.getId());
        result.setName(userToConvert.getName());
        result.setEmail(userToConvert.getEmail());
        result.setBirthday(userToConvert.getBirthday().format(DATE_FORMATTER));
        result.setRoles(userToConvert.getRoles());
        return result;
    }

    public static com.booking.beans.models.User convertToUser(User wsUser) {
        if(wsUser == null){
            return  null;
        }
        com.booking.beans.models.User result = new com.booking.beans.models.User();
        result.setId(wsUser.getId());
        result.setName(wsUser.getName());
        result.setEmail(wsUser.getEmail());
        result.setBirthday(LocalDate.parse(wsUser.getBirthday()));
        result.setRoles(wsUser.getRoles());
        result.setEncryptedPassword(wsUser.getEncryptedPassword());
        return result;
    }

}
