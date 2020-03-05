package com.mjakobczyk.bank.transfer.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashValidator;
import com.mjakobczyk.bank.transfer.model.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultTransferValidatorTest {

    @InjectMocks
    private DefaultTransferValidator testSubject;

    @Mock
    private AccountValidator accountValidator;

    @Mock
    private CashValidator cashValidator;

    @Mock
    private Account sourceAccount;

    @Mock
    private Account targetAccount;

    @Mock
    private Cash cash;

    @Mock
    private Transfer transfer;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultTransferValidator();
        initMocks(this);
    }

    @Test
    public void shouldNotBeValidForNotExistingTransfer() {
        // when
        final boolean result = testSubject.isValid(null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotBeValidForTransferWithInvalidSourceAccount() {
        // given
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(accountValidator.isValid(sourceAccount)).willReturn(false);

        // when
        final boolean result = testSubject.isValid(transfer);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotBeValidForTransferWithValidSourceAccountAndInvalidTargetAccount() {
        // given
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(accountValidator.isValid(sourceAccount)).willReturn(true);
        given(transfer.getTargetAccount()).willReturn(targetAccount);
        given(accountValidator.isValid(targetAccount)).willReturn(false);

        // when
        final boolean result = testSubject.isValid(transfer);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotBeValidForTransferWithInvalidCashAndValidAccounts() {
        // given
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(accountValidator.isValid(sourceAccount)).willReturn(true);
        given(transfer.getTargetAccount()).willReturn(targetAccount);
        given(accountValidator.isValid(targetAccount)).willReturn(true);
        given(transfer.getCash()).willReturn(cash);
        given(cashValidator.isValid(cash)).willReturn(false);

        // when
        final boolean result = testSubject.isValid(transfer);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldBeValidForTransferWithValidCashAndValidAccounts() {
        // given
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(accountValidator.isValid(sourceAccount)).willReturn(true);
        given(transfer.getTargetAccount()).willReturn(targetAccount);
        given(accountValidator.isValid(targetAccount)).willReturn(true);
        given(transfer.getCash()).willReturn(cash);
        given(cashValidator.isValid(cash)).willReturn(true);

        // when
        final boolean result = testSubject.isValid(transfer);

        // then
        assertThat(result).isTrue();
    }

}
