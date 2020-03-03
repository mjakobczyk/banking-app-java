package com.mjakobczyk.bank.account.service.impl;

import com.mjakobczyk.bank.account.service.AccountNumberGenerator;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Default implementation of {@link com.mjakobczyk.bank.account.service.AccountNumberGenerator}.
 */
public class DefaultAccountNumberGenerator implements AccountNumberGenerator {


    @Override
    public String generate() {
        return UUID.randomUUID().toString().replace("-", StringUtils.EMPTY);
    }
}
