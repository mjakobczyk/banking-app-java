package com.mjakobczyk.bank.transfer.service;

import com.mjakobczyk.bank.transfer.model.Transfer;

/**
 * Validator for {@link com.mjakobczyk.bank.transfer.model.Transfer}.
 */
public interface TransferValidator {

    boolean isValid(final Transfer transfer);

}
