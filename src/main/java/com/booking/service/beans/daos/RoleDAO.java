package com.booking.service.beans.daos;

import com.booking.service.beans.models.Role;

import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */
public interface RoleDAO {

    Role create(Role role);

    void delete(Role role);

    Role get(long id);

    Role getByName(String name);

    List<Role> getAll();

}
