package com.mjakobczyk.bank.cash.service;

import com.mjakobczyk.bank.cash.model.Cash;

/**
 * Validator for {@link com.mjakobczyk.bank.cash.model.Cash}.
 */
public interface CashValidator {

    boolean isValid(final Cash cash);
}
