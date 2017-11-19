package com.booking.beans.daos;

import com.booking.beans.models.User;
import com.booking.beans.models.UserAccount;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
public interface UserAccountDAO {

    User get(long id);

    void updateAccount(UserAccount account);

}
