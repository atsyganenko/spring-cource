package com.booking.service.beans.services;

import com.booking.service.beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private UserService userService;
    private Converter<User, UserDetails> userUserDetailsConverter;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserUserDetailsConverter(@Qualifier(value = "userToUserDetails") Converter<User, UserDetails> userUserDetailsConverter) {
        this.userUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(userService.getUserByEmail(email));
    }
}