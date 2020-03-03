package com.mjakobczyk.bank.account.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.model.AccountData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultAccountValidatorTest {

    private DefaultAccountValidator testSubject;

    @Mock
    private Account account;

    @Mock
    private AccountData accountData;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultAccountValidator();
        initMocks(this);
    }

    @Test
    public void shouldNotBeValidIfAccountDoesNotExist() {
        // when
        final boolean result = testSubject.isValid(null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotBeValidIfAccountDataForAccountDoesNotExist() {
        // given
        given(account.getAccountData()).willReturn(null);

        // when
        final boolean result = testSubject.isValid(account);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldBeValidIfAccountAndTheirAccountInfoExist() {
        // given
        given(account.getAccountData()).willReturn(accountData);

        // when
        final boolean result = testSubject.isValid(account);

        // then
        assertThat(result).isTrue();
    }

}
