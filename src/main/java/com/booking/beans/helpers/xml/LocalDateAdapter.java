package com.booking.service.beans.helpers.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/19/2017.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate unmarshal(String localDateStr) throws Exception {
        return LocalDate.parse(localDateStr, DATE_FORMATTER);
    }

    public String marshal(LocalDate localDate) throws Exception {
        return localDate.format(DATE_FORMATTER);
    }

}