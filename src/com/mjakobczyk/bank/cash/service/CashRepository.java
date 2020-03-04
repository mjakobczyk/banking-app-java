package com.mjakobczyk.bank.cash.service;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;

/**
 * Service responsible for storing {@link com.mjakobczyk.bank.cash.model.Cash}
 * in {@link com.mjakobczyk.bank.account.model.Account}.
 */
public interface CashRepository {

    Cash getCashFrom(final Account account);

    boolean saveCashIn(final Account account, final Cash cash);

}
