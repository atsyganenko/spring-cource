package com.booking.beans.services;

import com.booking.beans.daos.UserDAO;
import com.booking.beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:30 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User register(User user) {
        return userDAO.create(user);
    }

    @Override
    public void remove(User user) {
        userDAO.delete(user);
    }

    @Override
    public User getById(long id) {
        return userDAO.get(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDAO.getAllByName(name);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

}
