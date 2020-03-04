package com.mjakobczyk.bank.cash.exception;

/**
 * Exception indicating that {@link com.mjakobczyk.bank.cash.model.Cash} could not be deposited.
 */
public class CashDepositException extends RuntimeException {

    private static final String MESSAGE = "Cash could not be deposited";


    public CashDepositException() {
        super(CashDepositException.MESSAGE);
    }
}
