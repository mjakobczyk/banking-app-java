package com.mjakobczyk.bank.account.exception;

/**
 * Exception indicating that {@link com.mjakobczyk.bank.account.model.Account} does not exist.
 */
public class AccountDoesNotExistException extends RuntimeException {

    private static final String MESSAGE = "Account does not exist";

    public AccountDoesNotExistException() {
        super(MESSAGE);
    }
}
