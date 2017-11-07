package com.booking.service.beans.daos;

import com.booking.service.beans.models.User;
import com.booking.service.beans.models.UserAccount;
import com.booking.service.exceptions.AccountOperationsException;

import static com.booking.service.exceptions.AccountOperationsException.NON_ENOUGH_MONEY;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
public interface UserAccountDAO {

    User get(long id);

    void updateAccount(UserAccount account);

    default void validateAccount(UserAccount account) {
        if (account.getBalance() < 0) {
            throw new AccountOperationsException(NON_ENOUGH_MONEY);
        }
    }
}
