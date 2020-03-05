package com.mjakobczyk.bank.transfer.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashService;
import com.mjakobczyk.bank.transfer.model.Transfer;
import com.mjakobczyk.bank.transfer.service.TransferService;
import com.mjakobczyk.bank.transfer.service.TransferValidator;

/**
 * Default implementation of {@link com.mjakobczyk.bank.transfer.service.TransferService}.
 */
public class DefaultTransferService implements TransferService {

    private CashService cashService;
    private TransferValidator transferValidator;

    @Override
    public boolean perform(final Transfer transfer) {
        if (!transferValidator.isValid(transfer)) {
            return false;
        }

        final Account sourceAccount = transfer.getSourceAccount();
        final Account targetAccount = transfer.getTargetAccount();

        if (sourceAccount.equals(targetAccount)) {
            return false;
        }

        final Cash currentSourceAccountCash = cashService.getCurrentCash(sourceAccount);
        final Cash cashToTransfer = transfer.getCash();

        if (currentSourceAccountCash.getValue() < cashToTransfer.getValue()) {
            return false;
        }

        return cashService.withdraw(sourceAccount, cashToTransfer) &&
                cashService.deposit(targetAccount, cashToTransfer);
    }

    public CashService getCashService() {
        return cashService;
    }

    public void setCashService(final CashService cashService) {
        this.cashService = cashService;
    }

    public TransferValidator getTransferValidator() {
        return transferValidator;
    }

    public void setTransferValidator(final TransferValidator transferValidator) {
        this.transferValidator = transferValidator;
    }
}
