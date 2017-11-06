package com.booking.service.beans.daos.db;

import com.booking.service.beans.daos.AbstractDAO;
import com.booking.service.beans.daos.UserAccountDAO;
import com.booking.service.beans.models.User;
import com.booking.service.beans.models.UserAccount;
import org.springframework.stereotype.Repository;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */

@Repository(value = "accountDao")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {
    @Override
    public User get(long id) {

        return getCurrentSession().get(User.class, id);
    }

    @Override
    public void updateAccount(UserAccount account) {
        validateAccount(account);
        getCurrentSession().update(account);
    }
}
