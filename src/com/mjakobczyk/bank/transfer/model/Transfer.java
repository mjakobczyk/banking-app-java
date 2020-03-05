package com.mjakobczyk.bank.transfer.model;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;

/**
 * Model representing transfer that will be immutable after creation.
 */
public class Transfer {

    private final Account sourceAccount;
    private final Account targetAccount;
    private final Cash cash;

    public Transfer(final Account sourceAccount, final Account targetAccount, final Cash cash) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.cash = cash;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public Cash getCash() {
        return cash;
    }
}
