package com.mjakobczyk.bank.account.service;

/**
 * Service responsible for generating number for {@link com.mjakobczyk.bank.account.model.Account}.
 */
public interface AccountNumberGenerator {

    String generate();
}
