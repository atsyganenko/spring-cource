package com.booking.service.beans.view;

import com.booking.service.beans.models.Event;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/29/2017.
 */
public class EventPdfView extends AbstractPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        List<Event> events = (List<Event>) map.get("events");

        PdfPTable table = new PdfPTable(6);


        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Rate");
        table.addCell("Price");
        table.addCell("Date");
        table.addCell("Auditorium");

        for (Event event : events) {
            table.addCell(String.valueOf(event.getId()));
            table.addCell(event.getName());
            table.addCell(event.getRate().name());
            table.addCell(String.valueOf(event.getBasePrice()));
            table.addCell(event.getDateTime().format(DateTimeFormatter.BASIC_ISO_DATE));
            table.addCell(event.getAuditorium().getName());
        }

        document.add(table);
    }
}
