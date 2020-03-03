package com.mjakobczyk.bank.account.service.impl;

import com.mjakobczyk.bank.account.exception.AccountDoesNotExistException;
import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.model.AccountData;
import com.mjakobczyk.bank.account.service.AccountDataService;

/**
 * Default implementation of {@link com.mjakobczyk.bank.account.service.AccountDataService}.
 */
public class DefaultAccountDataService implements AccountDataService {

    public boolean update(final Account account, final AccountData accountData) {
        if (account == null) {
            throw new AccountDoesNotExistException();
        }

        if (accountData == null) {
            return false;
        }

        account.setAccountData(accountData);
        return true;
    }

}
