package com.booking.service.beans.configuration;

import com.booking.service.beans.view.TicketsPdfView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.document.AbstractPdfView;


@Configuration
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    TicketsPdfView ticketsPdfView() {
        return new TicketsPdfView();
    }
}
