package com.mjakobczyk.bank.credit.model;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;

/**
 * Model which represents {@link com.mjakobczyk.bank.account.model.Account} which
 * borrowed additional {@link com.mjakobczyk.bank.cash.model.Cash} for a specific
 * amount of instalments.
 */
public class Credit {

    private Account account;
    private Cash borrowedCash;
    private Cash cashLeftToPay;
    private int instalmentsCount;

    public Credit(final Account account, final Cash cashToBorrow, final int instalmentsCount) {
        this.account = account;
        this.borrowedCash = cashToBorrow;
        this.instalmentsCount = instalmentsCount;
        this.cashLeftToPay = calculateCashToPay(cashToBorrow, instalmentsCount);
    }

    protected Cash calculateCashToPay(final Cash cashToBorrow, final int instalmentsCount) {
        final double value = cashToBorrow.getValue() * (1 + 0.01 * instalmentsCount);
        return new Cash(value);
    }

    public Account getAccount() {
        return account;
    }

    public Cash getBorrowedCash() {
        return borrowedCash;
    }

    public Cash getCashLeftToPay() {
        return cashLeftToPay;
    }

    public int getInstalmentsCount() {
        return instalmentsCount;
    }
}
