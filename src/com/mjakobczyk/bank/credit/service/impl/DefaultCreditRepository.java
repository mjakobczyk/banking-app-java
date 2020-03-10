package com.mjakobczyk.bank.credit.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.credit.model.Credit;
import com.mjakobczyk.bank.credit.service.CreditRepository;
import com.mjakobczyk.bank.credit.service.CreditValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of {@link com.mjakobczyk.bank.credit.service.CreditRepository}.
 */
public class DefaultCreditRepository implements CreditRepository {

    private final Map<Account, List<Credit>> map;
    private AccountValidator accountValidator;
    private CreditValidator creditValidator;

    public DefaultCreditRepository() {
        this.map = new HashMap<>();
    }

    @Override
    public List<Credit> getCreditsOf(final Account account) {
        if (!accountValidator.isValid(account)) {
            return Collections.emptyList();
        }

        final List<Credit> accountCredits = getMap().get(account);
        return accountCredits != null ? accountCredits : Collections.emptyList();
    }

    @Override
    public boolean addCreditTo(final Account account, final Credit credit) {
        if (!accountValidator.isValid(account) || !creditValidator.isValid(credit)) {
            return false;
        }

        return getMap().computeIfAbsent(account, credits -> new ArrayList<>()).add(credit);
    }

    public AccountValidator getAccountValidator() {
        return accountValidator;
    }

    public void setAccountValidator(final AccountValidator accountValidator) {
        this.accountValidator = accountValidator;
    }

    public CreditValidator getCreditValidator() {
        return creditValidator;
    }

    public void setCreditValidator(final CreditValidator creditValidator) {
        this.creditValidator = creditValidator;
    }

    protected Map<Account, List<Credit>> getMap() {
        return map;
    }

}
