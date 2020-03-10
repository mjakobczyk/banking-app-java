package com.mjakobczyk.bank.credit.service;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.credit.model.Credit;

import java.util.List;

/**
 * Service responsible for storing {@link com.mjakobczyk.bank.credit.model.Credit}
 * linked with {@link com.mjakobczyk.bank.account.model.Account}.
 */
public interface CreditRepository {

    /**
     * Get all credits assigned to passed account.
     *
     * @param account whose credits are checked
     * @return list of credits
     */
    List<Credit> getCreditsOf(final Account account);

    /**
     * Add new credit to account.
     *
     * @param account that takes credit
     * @param credit  details
     * @return true if operation was successful, false otherwise
     */
    boolean addCreditTo(final Account account, final Credit credit);

}
