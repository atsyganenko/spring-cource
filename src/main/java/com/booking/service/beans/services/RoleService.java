package com.booking.service.beans.services;

import com.booking.service.beans.models.Role;

import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */
public interface RoleService {

    Role getById(long id);

    Role getByName(String name);

    List<Role> getAll();

    void createRole(Role role);
}
