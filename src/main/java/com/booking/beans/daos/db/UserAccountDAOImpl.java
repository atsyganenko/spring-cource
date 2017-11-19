package com.booking.beans.daos.db;

import com.booking.beans.daos.AbstractDAO;
import com.booking.beans.daos.UserAccountDAO;
import com.booking.beans.models.User;
import com.booking.beans.models.UserAccount;
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
        getCurrentSession().update(account);
    }
}
