package com.booking.service.beans.helpers;


import com.booking.service.beans.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/5/2017.
 */
public class UserToUserDetailsConverterTest {

    @Test
    public void shouldConvertUserToUserDetails() {
        UserToUserDetailsConverter target = new UserToUserDetailsConverter();

        String name = "testUser";
        String encryptedPassword = "hgjldgjlasdjaiej78jhdsmla";
        String roles = "TEST_USER,TEST_ADMIN";

        User user = new User();
        user.setName(name);
        user.setEncryptedPassword(encryptedPassword);
        user.setRoles(roles);

        UserDetails userDetails = target.convert(user);
        Assert.assertEquals(name, userDetails.getUsername());
        Assert.assertEquals(encryptedPassword, userDetails.getPassword());
        Assert.assertEquals(true, userDetails.isAccountNonExpired());
        Assert.assertEquals(true, userDetails.isAccountNonLocked());
        Assert.assertEquals(true, userDetails.isCredentialsNonExpired());
        Assert.assertEquals(true, userDetails.isEnabled());
        Assert.assertEquals(2, userDetails.getAuthorities().size());
        Assert.assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEST_USER")));
        Assert.assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEST_ADMIN")));
    }

}
