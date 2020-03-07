package com.mjakobczyk.bank.credit.service;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.credit.model.Credit;

/**
 * Service responsible for taking and paying off {@link com.mjakobczyk.bank.credit.model.Credit}.
 */
public interface CreditService {

    boolean take(final Credit credit);

}
