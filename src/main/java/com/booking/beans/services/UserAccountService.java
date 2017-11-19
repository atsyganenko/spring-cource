package com.booking.beans.services;

import com.booking.beans.models.UserAccount;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
interface UserAccountService {

   void withdrawMoney(UserAccount account, double amount);

   void topUpAccount(UserAccount account, double amount);
}
