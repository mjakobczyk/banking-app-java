package com.mjakobczyk.bank.account.model;

/**
 * Model which gives an access to the banking application.
 */
public class Account {

    private AccountData accountData;
    private String number;

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(final AccountData accountData) {
        this.accountData = accountData;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }
}
