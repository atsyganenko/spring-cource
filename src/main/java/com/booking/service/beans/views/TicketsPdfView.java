package com.booking.service.beans.views;

import com.booking.service.beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/29/2017.
 */
public class TicketsPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        List<Ticket> tickets = (List<Ticket>) map.get("tickets");

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

        document.add(table);
    }
}
