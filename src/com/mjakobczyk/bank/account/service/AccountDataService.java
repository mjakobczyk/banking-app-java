package com.mjakobczyk.bank.account.service;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.model.AccountData;

/**
 * Service responsible for updating {@link com.mjakobczyk.bank.account.model.AccountData}
 * in {@link com.mjakobczyk.bank.account.model.Account}.
 */
public interface AccountDataService {

    boolean update(final Account account, final AccountData accountData);
}
