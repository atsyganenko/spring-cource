package com.booking.service.beans.helpers.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/19/2017.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public LocalDateTime unmarshal(String localDateTimeStr) throws Exception {
        return LocalDateTime.parse(localDateTimeStr, DATE_TIME_FORMATTER);
    }

    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

}