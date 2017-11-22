package com.booking.service.beans.helpers;

import com.booking.service.beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/21/2017.
 */
public class PdfHttpMessageConverter extends AbstractHttpMessageConverter<List<Object>> {

    public PdfHttpMessageConverter() {
        super(MediaType.APPLICATION_PDF);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return List.class.isAssignableFrom(aClass);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    protected List<Object> readInternal(Class<? extends List<Object>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(List<Object> list, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {


        ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream(4096);
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, tempOutputStream);
            writer.setViewerPreferences(2053);
            document.open();

            PdfPTable table = new PdfPTable(6);
            table.addCell("ID");
            table.addCell("Event");
            table.addCell("Date");
            table.addCell("Seats");
            table.addCell("User");
            table.addCell("Price");

            list.forEach(item -> {
                if (item instanceof Ticket) {
                    table.addCell(String.valueOf(((Ticket) item).getId()));
                    table.addCell(((Ticket) item).getEvent().getName());
                    table.addCell(((Ticket) item).getDateTime().format(DateTimeFormatter.BASIC_ISO_DATE));
                    table.addCell(((Ticket) item).getSeats());
                    table.addCell(((Ticket) item).getUser().getName());
                    table.addCell(String.valueOf(((Ticket) item).getPrice()));
                }
            });
            document.add(table);
            document.close();

            OutputStream out = httpOutputMessage.getBody();
            tempOutputStream.writeTo(out);
            out.flush();
        } catch (DocumentException e) {
            throw new HttpMessageNotWritableException(e.getMessage());
        }
    }
}
