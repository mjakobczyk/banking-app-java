package com.mjakobczyk.bank.credit.service;

import com.mjakobczyk.bank.credit.model.Credit;

/**
 * Service responsible for taking and paying off {@link com.mjakobczyk.bank.credit.model.Credit}.
 */
public interface CreditService {

    /**
     * Perform taking credit operation.
     *
     * @param credit information
     * @return true if operation was successful, false otherwise
     */
    boolean take(final Credit credit);

}
