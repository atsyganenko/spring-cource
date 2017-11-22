package com.booking.service.beans.helpers;

import com.booking.service.beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
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
import java.util.List;

import static com.booking.service.beans.helpers.PdfGenerationUtil.generateTicketsPdfTable;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/21/2017.
 */
public class PdfHttpMessageConverter extends AbstractHttpMessageConverter<List<Ticket>> {

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
    protected List<Ticket> readInternal(Class<? extends List<Ticket>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(List<Ticket> tickets, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {


        ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream(4096);
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, tempOutputStream);
            writer.setViewerPreferences(2053);
            document.open();

            document.add(generateTicketsPdfTable(tickets));

            document.close();

            OutputStream out = httpOutputMessage.getBody();
            tempOutputStream.writeTo(out);
            out.flush();
        } catch (DocumentException e) {
            throw new HttpMessageNotWritableException(String.format("Convert to PDF failed. Reason: %s", e.getMessage()));
        }
    }
}
