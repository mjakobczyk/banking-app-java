package com.mjakobczyk.bank.account.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.service.AccountValidator;

/**
 * Default implementation of {@link com.mjakobczyk.bank.account.service.AccountValidator}.
 */
public class DefaultAccountValidator implements AccountValidator {

    @Override
    public boolean isValid(final Account account) {
        return account != null && account.getAccountData() != null;
    }
}
