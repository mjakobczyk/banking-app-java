package com.mjakobczyk.bank.cash.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashRepository;
import com.mjakobczyk.bank.cash.service.CashService;
import com.mjakobczyk.bank.cash.service.CashValidator;

/**
 * Default implementation of {@link com.mjakobczyk.bank.cash.service.CashService}.
 */
public class DefaultCashService implements CashService {

    private AccountValidator accountValidator;
    private CashValidator cashValidator;
    private CashRepository cashRepository;

    public boolean deposit(final Account account, final Cash cash) {
        if (!accountValidator.isValid(account) || !cashValidator.isValid(cash)) {
            return false;
        }

        final Cash accountCash = getCurrentCash(account);
        if (accountCash == null) {
            return cashRepository.saveCashIn(account, cash);
        }

        accountCash.add(cash);
        return cashRepository.saveCashIn(account, accountCash);
    }

    public boolean withdraw(final Account account, final Cash cash) {
        if (!accountValidator.isValid(account) || !cashValidator.isValid(cash)) {
            return false;
        }

        final Cash accountCash = getCurrentCash(account);
        if (accountCash == null || accountCash.getValue() < cash.getValue()) {
            return false;
        }

        accountCash.subtract(cash);
        return cashRepository.saveCashIn(account, accountCash);
    }

    public Cash getCurrentCash(final Account account) {
        return cashRepository.getCashFrom(account);
    }
}
