package com.booking.service.beans.services;

import com.booking.service.beans.daos.RoleDAO;
import com.booking.service.beans.daos.UserDAO;
import com.booking.service.beans.models.Role;
import com.booking.service.beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/4/2017.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleDAO roleDAO;

    public RoleServiceImpl(@Qualifier("roleDAO") RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    public Role getById(long id) {
        return roleDAO.get(id);
    }

    @Override
    public Role getByName(String name) {
        return roleDAO.getByName(name);
    }

    @Override
    public void createRole(Role role) {
        roleDAO.create(role);
    }

    @Override
    public List<Role> getAll() {
        return roleDAO.getAll();
    }
}
