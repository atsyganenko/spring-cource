package com.booking.service.beans.models;

import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "booking/rate")
public enum Rate {
    HIGH, MID, LOW
}
