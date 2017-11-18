package com.booking.service.beans.daos;

import com.booking.service.beans.models.User;
import com.booking.service.beans.models.UserAccount;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
public interface UserAccountDAO {

    User get(long id);

    void updateAccount(UserAccount account);

}
