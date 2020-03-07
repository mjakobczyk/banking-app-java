package com.mjakobczyk.bank.credit.service;

import com.mjakobczyk.bank.credit.model.Credit;

/**
 * Validator for {@link com.mjakobczyk.bank.credit.model.Credit}.
 */
public interface CreditValidator {

    boolean isValid(final Credit credit);

}
