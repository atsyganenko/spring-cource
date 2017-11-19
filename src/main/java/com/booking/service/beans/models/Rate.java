package com.booking.service.beans.models;

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "http://ws/booking/models")
public enum Rate {
    HIGH, MID, LOW
}
