package com.mjakobczyk.bank.credit.service.impl;

import com.mjakobczyk.bank.credit.model.Credit;
import com.mjakobczyk.bank.credit.service.CreditRepository;
import com.mjakobczyk.bank.credit.service.CreditService;
import com.mjakobczyk.bank.credit.service.CreditValidator;

/**
 * Default implementation of {@link com.mjakobczyk.bank.credit.service.CreditService}.
 */
public class DefaultCreditService implements CreditService {

    private CreditRepository creditRepository;
    private CreditValidator creditValidator;

    @Override
    public boolean take(final Credit credit) {
        if (!creditValidator.isValid(credit)) {
            return false;
        }

        return creditRepository.addCreditTo(credit.getAccount(), credit);
    }

    public CreditRepository getCreditRepository() {
        return creditRepository;
    }

    public void setCreditRepository(final CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public CreditValidator getCreditValidator() {
        return creditValidator;
    }

    public void setCreditValidator(final CreditValidator creditValidator) {
        this.creditValidator = creditValidator;
    }
}
