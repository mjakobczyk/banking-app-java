package com.mjakobczyk.bank.credit.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.credit.model.Credit;
import com.mjakobczyk.bank.credit.service.CreditRepository;
import com.mjakobczyk.bank.credit.service.CreditValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCreditServiceTest {

    @InjectMocks
    private DefaultCreditService testSubject;

    @Mock
    private CreditRepository creditRepository;

    @Mock
    private CreditValidator creditValidator;

    @Mock
    private Account account;

    @Mock
    private Credit credit;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultCreditService();
        initMocks(this);
    }

    @Test
    public void shouldNotTakeCreditForInvalidCreditData() {
        // given
        given(creditValidator.isValid(credit)).willReturn(false);

        // when
        final boolean result = testSubject.take(credit);

        // then
        assertThat(result).isFalse();
        then(creditRepository).should(never()).addCreditTo(any(Account.class), any(Credit.class));
    }

    @Test
    public void shouldNotTakeCreditIfAddingCreditOperationWasNotSuccessful() {
        //given
        given(creditValidator.isValid(credit)).willReturn(true);
        given(creditRepository.addCreditTo(account, credit)).willReturn(false);

        // when
        final boolean result = testSubject.take(credit);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldTakeCreditIfAddingCreditOperationWasSuccessful() {
        //given
        given(creditValidator.isValid(credit)).willReturn(true);
        given(credit.getAccount()).willReturn(account);
        given(creditRepository.addCreditTo(account, credit)).willReturn(true);

        // when
        final boolean result = testSubject.take(credit);

        // then
        assertThat(result).isTrue();
        then(creditRepository).should().addCreditTo(account, credit);
    }

}
