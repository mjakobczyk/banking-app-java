package com.mjakobczyk.bank.transfer.service.impl;

import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.cash.service.CashValidator;
import com.mjakobczyk.bank.transfer.model.Transfer;
import com.mjakobczyk.bank.transfer.service.TransferValidator;

/**
 * Default implementation of {@link com.mjakobczyk.bank.transfer.service.TransferValidator}.
 */
public class DefaultTransferValidator implements TransferValidator {

    private AccountValidator accountValidator;
    private CashValidator cashValidator;

    @Override
    public boolean isValid(final Transfer transfer) {
        return transfer != null && accountValidator.isValid(transfer.getSourceAccount()) &&
                accountValidator.isValid(transfer.getTargetAccount()) &&
                cashValidator.isValid(transfer.getCash());
    }

    public AccountValidator getAccountValidator() {
        return accountValidator;
    }

    public void setAccountValidator(final AccountValidator accountValidator) {
        this.accountValidator = accountValidator;
    }

    public CashValidator getCashValidator() {
        return cashValidator;
    }

    public void setCashValidator(final CashValidator cashValidator) {
        this.cashValidator = cashValidator;
    }
}
