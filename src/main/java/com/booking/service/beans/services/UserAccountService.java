package com.booking.service.beans.services;

import com.booking.service.beans.models.UserAccount;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/6/2017.
 */
public interface UserAccountService {

   void withdrawMoney(UserAccount account, double amount);
}
