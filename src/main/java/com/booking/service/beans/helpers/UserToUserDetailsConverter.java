package com.booking.service.beans.helpers;
/*
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */

import com.booking.service.beans.models.SecurityUserDetails;
import com.booking.service.beans.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component("userToUserDetails")
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User user) {
        SecurityUserDetails userDetails = new SecurityUserDetails();

        if (user != null) {
            userDetails.setUsername(user.getName());
            userDetails.setPassword(user.getEncryptedPassword());
            userDetails.setEnabled(true);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRolesSet().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role)));
            });
            userDetails.setAuthorities(authorities);
        }

        return userDetails;
    }
}