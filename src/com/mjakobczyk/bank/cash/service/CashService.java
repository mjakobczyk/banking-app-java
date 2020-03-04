package com.mjakobczyk.bank.cash.service;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;

/**
 * Service responsible for performing operations on {@link com.mjakobczyk.bank.cash.model.Cash}
 * that belong to {@link com.mjakobczyk.bank.account.model.Account}.
 */
public interface CashService {

    boolean deposit(final Account account, final Cash cash);

    boolean withdraw(final Account account, final Cash cash);

    Cash getCurrentCash(final Account account);

}
