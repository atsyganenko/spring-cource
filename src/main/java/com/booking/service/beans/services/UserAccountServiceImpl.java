package com.booking.service.beans.services;

import com.booking.service.beans.daos.UserAccountDAO;
import com.booking.service.beans.models.UserAccount;
import com.booking.service.exceptions.TicketBookingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static com.booking.service.exceptions.TicketBookingException.NEGATIVE_AMOUNT;
import static com.booking.service.exceptions.TicketBookingException.NON_ENOUGH_MONEY;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDAO userAccountDAO;

    @Autowired
    public UserAccountServiceImpl(@Qualifier("accountDao") UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    @Override
    public void withdrawMoney(UserAccount account, double amount) {
        if(amount < 0) throw new TicketBookingException(NEGATIVE_AMOUNT);
        double remainingBalance = account.getBalance() - amount;
        if (remainingBalance >= 0) {
            account.setBalance(remainingBalance);
            userAccountDAO.updateAccount(account);
        } else {
            throw new TicketBookingException(NON_ENOUGH_MONEY);
        }
    }
}
