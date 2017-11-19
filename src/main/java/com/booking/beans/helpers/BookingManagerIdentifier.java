package com.booking.beans.helpers;

import com.booking.beans.models.UserRole;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/18/2017.
 */
public class BookingManagerIdentifier extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (request.isUserInRole(UserRole.BOOKING_MANAGER.name())) {
            modelAndView.getModel().put("isBookingManager", true);
        }
    }
}
