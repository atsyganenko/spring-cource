package com.booking.beans.models;

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://booking.com/booking-web-service")
public enum Rate {
    HIGH, MID, LOW
}
