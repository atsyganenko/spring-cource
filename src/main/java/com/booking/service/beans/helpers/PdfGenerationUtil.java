package com.booking.service.beans.helpers;

import com.booking.service.beans.models.Ticket;
import com.lowagie.text.pdf.PdfPTable;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/22/2017.
 */
public class PdfGenerationUtil {

    public static PdfPTable generateTicketsPdfTable(List<Ticket> tickets) {
        PdfPTable table = new PdfPTable(6);
        table.addCell("ID");
        table.addCell("Event");
        table.addCell("Date");
        table.addCell("Seats");
        table.addCell("User");
        table.addCell("Price");

        for (Ticket ticket : tickets) {
            table.addCell(String.valueOf(ticket.getId()));
            table.addCell(ticket.getEvent().getName());
            table.addCell(ticket.getDateTime().format(DateTimeFormatter.BASIC_ISO_DATE));
            table.addCell(ticket.getSeats());
            table.addCell(ticket.getUser().getName());
            table.addCell(String.valueOf(ticket.getPrice()));
        }
        return table;
    }

    private PdfGenerationUtil() {
    }

}
