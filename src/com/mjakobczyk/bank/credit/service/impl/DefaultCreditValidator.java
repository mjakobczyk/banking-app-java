package com.mjakobczyk.bank.credit.service.impl;

import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.cash.service.CashValidator;
import com.mjakobczyk.bank.credit.model.Credit;
import com.mjakobczyk.bank.credit.service.CreditValidator;

/**
 * Default implementation of {@link com.mjakobczyk.bank.credit.service.CreditValidator}.
 */
public class DefaultCreditValidator implements CreditValidator {

    private AccountValidator accountValidator;
    private CashValidator cashValidator;

    @Override
    public boolean isValid(final Credit credit) {
        return credit != null && checkAllValidatorsFor(credit) && credit.getInstalmentsCount() > 0;
    }

    protected boolean checkAllValidatorsFor(final Credit credit) {
        return accountValidator.isValid(credit.getAccount()) &&
                cashValidator.isValid(credit.getBorrowedCash()) &&
                cashValidator.isValid(credit.getCashLeftToPay());
    }

}
