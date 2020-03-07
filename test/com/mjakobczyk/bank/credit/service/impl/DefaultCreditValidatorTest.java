package com.mjakobczyk.bank.credit.service.impl;


import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashValidator;
import com.mjakobczyk.bank.credit.model.Credit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCreditValidatorTest {

    private static final int INCORRECT_INSTALMENTS_COUNT = 0;
    private static final int CORRECT_INSTALMENTS_COUNT = 1;


    @InjectMocks
    private DefaultCreditValidator testSubject;

    @Mock
    private AccountValidator accountValidator;

    @Mock
    private CashValidator cashValidator;

    @Mock
    private Account account;

    @Mock
    private Cash borrowedCash;

    @Mock
    private Cash cashLeftToPay;

    @Mock
    private Credit credit;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultCreditValidator();
        initMocks(this);
    }

    @Test
    public void shouldNotBeValidForNotExistingCredit() {
        // when
        final boolean result = testSubject.isValid(null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotBeValidForInvalidAccount() {
        // given
        given(credit.getAccount()).willReturn(account);
        given(accountValidator.isValid(account)).willReturn(false);

        // when
        final boolean result = testSubject.isValid(credit);

        // then
        assertThat(result).isFalse();
        then(accountValidator).should().isValid(account);
    }

    @Test
    public void shouldNotBeValidForInvalidBorrowedCash() {
        // given
        given(credit.getAccount()).willReturn(account);
        given(accountValidator.isValid(account)).willReturn(true);
        given(credit.getBorrowedCash()).willReturn(borrowedCash);
        given(cashValidator.isValid(borrowedCash)).willReturn(false);

        // when
        final boolean result = testSubject.isValid(credit);

        // then
        assertThat(result).isFalse();
        then(accountValidator).should().isValid(account);
        then(cashValidator).should().isValid(borrowedCash);
    }

    @Test
    public void shouldNotBeValidForInvalidCashLeftToPay() {
        // given
        given(credit.getAccount()).willReturn(account);
        given(accountValidator.isValid(account)).willReturn(true);
        given(credit.getBorrowedCash()).willReturn(borrowedCash);
        given(cashValidator.isValid(borrowedCash)).willReturn(true);
        given(credit.getCashLeftToPay()).willReturn(cashLeftToPay);
        given(cashValidator.isValid(cashLeftToPay)).willReturn(false);

        // when
        final boolean result = testSubject.isValid(credit);

        // then
        assertThat(result).isFalse();
        then(accountValidator).should().isValid(account);
        then(cashValidator).should().isValid(borrowedCash);
        then(cashValidator).should().isValid(cashLeftToPay);
    }

    @Test
    public void shouldNotBeValidForInvalidInstalmentsCount() {
        // given
        given(credit.getAccount()).willReturn(account);
        given(accountValidator.isValid(account)).willReturn(true);
        given(credit.getBorrowedCash()).willReturn(borrowedCash);
        given(cashValidator.isValid(borrowedCash)).willReturn(true);
        given(credit.getCashLeftToPay()).willReturn(cashLeftToPay);
        given(cashValidator.isValid(cashLeftToPay)).willReturn(true);
        given(credit.getInstalmentsCount()).willReturn(INCORRECT_INSTALMENTS_COUNT);

        // when
        final boolean result = testSubject.isValid(credit);

        // then
        assertThat(result).isFalse();
        then(accountValidator).should().isValid(account);
        then(cashValidator).should().isValid(borrowedCash);
        then(cashValidator).should().isValid(cashLeftToPay);
    }

    @Test
    public void shouldBeValidForAllValidParameters() {
        // given
        given(credit.getAccount()).willReturn(account);
        given(accountValidator.isValid(account)).willReturn(true);
        given(credit.getBorrowedCash()).willReturn(borrowedCash);
        given(cashValidator.isValid(borrowedCash)).willReturn(true);
        given(credit.getCashLeftToPay()).willReturn(cashLeftToPay);
        given(cashValidator.isValid(cashLeftToPay)).willReturn(true);
        given(credit.getInstalmentsCount()).willReturn(CORRECT_INSTALMENTS_COUNT);

        // when
        final boolean result = testSubject.isValid(credit);

        // then
        assertThat(result).isTrue();
        then(accountValidator).should().isValid(account);
        then(cashValidator).should().isValid(borrowedCash);
        then(cashValidator).should().isValid(cashLeftToPay);
    }

}
