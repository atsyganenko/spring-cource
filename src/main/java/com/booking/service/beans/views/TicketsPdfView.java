package com.booking.service.beans.views;

import com.booking.service.beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.booking.service.beans.helpers.PdfGenerationUtil.generateTicketsPdfTable;

/**
 * Created by Anastasiia Tsyganenko
 * on 10/29/2017.
 */
public class TicketsPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        List<Ticket> tickets = (List<Ticket>) map.get("tickets");

        document.add(generateTicketsPdfTable(tickets));
    }
}
