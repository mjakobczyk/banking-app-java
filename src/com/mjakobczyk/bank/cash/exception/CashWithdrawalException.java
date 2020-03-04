package com.mjakobczyk.bank.cash.exception;

/**
 * Exception indicating that {@link com.mjakobczyk.bank.cash.model.Cash} could not be withdrawn.
 */
public class CashWithdrawalException extends RuntimeException {

    private static final String MESSAGE = "Cash could not be withdrawn";


    public CashWithdrawalException() {
        super(CashWithdrawalException.MESSAGE);
    }
}
