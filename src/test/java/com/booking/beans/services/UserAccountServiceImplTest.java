package com.booking.beans.services;

import com.booking.beans.daos.UserAccountDAO;
import com.booking.beans.models.UserAccount;
import com.booking.exceptions.AccountOperationsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountServiceImplTest {

    @Mock
    private UserAccountDAO userAccountDAO;
    private UserAccountService target;
    private UserAccount testAccount;
    private static final double STARTING_BALANCE = 100;

    @Before
    public void setUp() {
        target = new UserAccountServiceImpl(userAccountDAO);
        testAccount = new UserAccount();
        testAccount.setBalance(STARTING_BALANCE);
    }

    @Test
    public void shouldWithdrawWhenAmountLessThanBalance() throws Exception {
        double amountToWithdraw = 50;
        target.withdrawMoney(testAccount, amountToWithdraw);
        Assert.assertTrue(Double.valueOf(STARTING_BALANCE - amountToWithdraw).equals(testAccount.getBalance()));
        Mockito.verify(userAccountDAO).updateAccount(Mockito.eq(testAccount));
    }

    @Test
    public void shouldWithdrawWhenAmountEqualsBalance() throws Exception {
        double amountToWithdraw = STARTING_BALANCE;
        target.withdrawMoney(testAccount, amountToWithdraw);
        Assert.assertTrue(Double.valueOf(STARTING_BALANCE - amountToWithdraw).equals(testAccount.getBalance()));
        Mockito.verify(userAccountDAO).updateAccount(Mockito.eq(testAccount));
    }

    @Test(expected = AccountOperationsException.class)
    public void shouldThrowExceptionWhenAmountLargerThanBalance() throws Exception {
        double amountToWithdraw = STARTING_BALANCE + 50;
        target.withdrawMoney(testAccount, amountToWithdraw);
        Assert.assertTrue(Double.valueOf(STARTING_BALANCE - amountToWithdraw).equals(testAccount.getBalance()));
    }

    @Test(expected = AccountOperationsException.class)
    public void shouldThrowExceptionWhenNegativeAmount() throws Exception {
        double amountToWithdraw = - 50;
        target.withdrawMoney(testAccount, amountToWithdraw);
        Assert.assertTrue(Double.valueOf(STARTING_BALANCE - amountToWithdraw).equals(testAccount.getBalance()));
    }
}