package com.mjakobczyk.bank.account.service;

import com.mjakobczyk.bank.account.model.Account;

/**
 * Validator for {@link com.mjakobczyk.bank.account.model.Account}.
 */
public interface AccountValidator {

    boolean isValid(final Account account);
}
