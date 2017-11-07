package com.booking.service.beans.services;

import com.booking.service.beans.daos.UserAccountDAO;
import com.booking.service.beans.models.UserAccount;
import com.booking.service.exceptions.AccountOperationsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;import static com.booking.service.exceptions.AccountOperationsException.*;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
@Service
@Transactional
class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDAO userAccountDAO;

    @Autowired
    public UserAccountServiceImpl(@Qualifier("accountDao") UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    @Override
    public void withdrawMoney(UserAccount account, double amount) {
        if(amount < 0) throw new AccountOperationsException(NEGATIVE_AMOUNT_WITHDRAW);
        double remainingBalance = account.getBalance() - amount;
        if (remainingBalance >= 0) {
            account.setBalance(remainingBalance);
            userAccountDAO.updateAccount(account);
        } else {
            throw new AccountOperationsException(NON_ENOUGH_MONEY);
        }
    }

    @Override
    public void topUpAccount(UserAccount account, double amount) {
        if(amount < 0) throw new AccountOperationsException(NEGATIVE_AMOUNT_TOP_UP);
        account.setBalance(account.getBalance() + amount);
        userAccountDAO.updateAccount(account);
    }
}
