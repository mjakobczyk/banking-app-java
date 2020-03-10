package com.mjakobczyk.bank.credit.service.impl;


import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.credit.model.Credit;
import com.mjakobczyk.bank.credit.service.CreditValidator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCreditRepositoryTest {

    @Spy
    @InjectMocks
    private DefaultCreditRepository testSubject;

    @Mock
    private AccountValidator accountValidator;

    @Mock
    private CreditValidator creditValidator;

    @Mock
    private Account account;

    @Mock
    private Credit credit;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultCreditRepository();
        initMocks(this);
    }

    @Test
    public void shouldNotGetCreditsOfInvalidAccount() {
        // given
        given(accountValidator.isValid(account)).willReturn(false);

        // when
        final List<Credit> result = testSubject.getCreditsOf(account);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnEmptyCreditsListIfAccountHadNoCreditAssigned() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);

        // when
        final List<Credit> result = testSubject.getCreditsOf(account);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnCreditsListIfAccountHadCreditAssigned() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        doReturn(Collections.singletonMap(account, Lists.newArrayList(credit))).when(testSubject).getMap();

        // when
        final List<Credit> result = testSubject.getCreditsOf(account);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).contains(credit);
    }

    @Test
    public void shouldNotAddCreditToAccountIfAccountIsInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(false);

        // when
        final boolean result = testSubject.addCreditTo(account, credit);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotAddCreditToAccountIfAccountIsValidAndCreditIsInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(creditValidator.isValid(credit)).willReturn(false);

        // when
        final boolean result = testSubject.addCreditTo(account, credit);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldAddCreditToAccountIfParamsAreValid() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(creditValidator.isValid(credit)).willReturn(true);

        // when
        final boolean result = testSubject.addCreditTo(account, credit);

        // then
        assertThat(result).isTrue();
    }

}
