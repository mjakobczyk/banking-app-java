package com.mjakobczyk.bank.cash.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link com.mjakobczyk.bank.cash.service.CashRepository}.
 */
public class DefaultCashRepository implements CashRepository {

    private final Map<Account, Cash> map;

    public DefaultCashRepository() {
        this.map = new HashMap<>();
    }

    @Override
    public Cash getCashFrom(final Account account) {
        if (account != null) {
            return getMap().get(account);
        }

        return null;
    }

    @Override
    public boolean saveCashIn(final Account account, final Cash cash) {
        if (account != null && cash != null) {
            getMap().put(account, cash);
            return true;
        }

        return false;
    }

    private Map<Account, Cash> getMap() {
        return map;
    }
}
