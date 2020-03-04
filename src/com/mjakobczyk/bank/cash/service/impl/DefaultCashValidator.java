package com.mjakobczyk.bank.cash.service.impl;

import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashValidator;

/**
 * Default implementation of {@link com.mjakobczyk.bank.cash.service.CashValidator}.
 */
public class DefaultCashValidator implements CashValidator {

    @Override
    public boolean isValid(final Cash cash) {
        return cash != null && cash.getValue() >= 0;
    }

}
