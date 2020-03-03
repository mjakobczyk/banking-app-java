package com.mjakobczyk.bank.account.service.impl;

import com.mjakobczyk.bank.account.exception.AccountDoesNotExistException;
import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.model.AccountData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultAccountDataServiceTest {

    private DefaultAccountDataService testSubject;

    @Mock
    private Account account;

    @Mock
    private AccountData accountData;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultAccountDataService();
        initMocks(this);
    }

    @Test
    public void shouldNotUpdateAccountDataForNotExistingAccount() {
        // when
        Assertions.assertThrows(AccountDoesNotExistException.class, () ->
                testSubject.update(null, null));
    }

    @Test
    public void shouldNotUpdateAccountDataForExistingAccountAndNotExistingAccountData() {
        // when
        final boolean result = testSubject.update(account, null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldUpdateAccountDataForExistingAccountAndExistingAccountData() {
        // when
        final boolean result = testSubject.update(account, accountData);

        // then
        assertThat(result).isTrue();
    }

}