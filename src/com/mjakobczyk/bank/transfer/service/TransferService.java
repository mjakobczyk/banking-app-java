package com.mjakobczyk.bank.transfer.service;

import com.mjakobczyk.bank.transfer.model.Transfer;

/**
 * Service responsible for transferring {@link com.mjakobczyk.bank.cash.model.Cash}
 * between different {@link com.mjakobczyk.bank.account.model.Account}.
 */
public interface TransferService {

    boolean perform(final Transfer transfer);

}
