package com.booking.service.beans.configuration;

import com.booking.service.beans.helpers.BookingManagerIdentifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BookingManagerIdentifier()).excludePathPatterns("/rest/**");
    }
}
