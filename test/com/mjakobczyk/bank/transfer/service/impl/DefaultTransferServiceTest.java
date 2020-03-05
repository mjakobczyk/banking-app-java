package com.mjakobczyk.bank.transfer.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashService;
import com.mjakobczyk.bank.transfer.model.Transfer;
import com.mjakobczyk.bank.transfer.service.TransferValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultTransferServiceTest {

    private static final double LOW_CASH_VALUE = 1.0D;
    private static final double HIGH_CASH_VALUE = 1.0D;


    @InjectMocks
    private DefaultTransferService testSubject;

    @Mock
    private CashService cashService;

    @Mock
    private TransferValidator transferValidator;

    @Mock
    private Account sourceAccount;

    @Mock
    private Account targetAccount;

    @Mock
    private Cash cashOfSourceAccount;

    @Mock
    private Cash cashToTransfer;

    @Mock
    private Transfer transfer;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultTransferService();
        initMocks(this);
    }

    @Test
    public void shouldNotPerformTransferForInvalidTransfer() {
        // given
        given(transferValidator.isValid(transfer)).willReturn(false);

        // when
        final boolean hasPerformed = testSubject.perform(transfer);

        // then
        assertThat(hasPerformed).isFalse();
    }

    @Test
    public void shouldNotPerformTransferIfAccountsAreTheSame() {
        // given
        given(transferValidator.isValid(transfer)).willReturn(true);
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(transfer.getTargetAccount()).willReturn(sourceAccount);

        // when
        final boolean hasPerformed = testSubject.perform(transfer);

        // then
        assertThat(hasPerformed).isFalse();
    }

    @Test
    public void shouldNotPerformTransferIfSourceAccountDoesNotHaveSufficientCashValue() {
        // given
        given(transferValidator.isValid(transfer)).willReturn(true);
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(transfer.getTargetAccount()).willReturn(targetAccount);
        given(transfer.getCash()).willReturn(cashToTransfer);
        given(cashToTransfer.getValue()).willReturn(HIGH_CASH_VALUE);
        given(cashService.getCurrentCash(sourceAccount)).willReturn(cashOfSourceAccount);
        given(cashOfSourceAccount.getValue()).willReturn(LOW_CASH_VALUE);

        // when
        final boolean hasPerformed = testSubject.perform(transfer);

        // then
        assertThat(hasPerformed).isFalse();
    }

    @Test
    public void shouldNotPerformTransferIfCashCouldNotBeWithdrawnFromSourceAccount() {
        // given
        given(transferValidator.isValid(transfer)).willReturn(true);
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(transfer.getTargetAccount()).willReturn(targetAccount);
        given(transfer.getCash()).willReturn(cashToTransfer);
        given(cashToTransfer.getValue()).willReturn(LOW_CASH_VALUE);
        given(cashService.getCurrentCash(sourceAccount)).willReturn(cashOfSourceAccount);
        given(cashOfSourceAccount.getValue()).willReturn(HIGH_CASH_VALUE);
        given(cashService.withdraw(sourceAccount, cashToTransfer)).willReturn(false);

        // when
        final boolean hasPerformed = testSubject.perform(transfer);

        // then
        assertThat(hasPerformed).isFalse();
    }

    @Test
    public void shouldNotPerformTransferIfCashCouldNotBeDepositedInTargetAccount() {
        // given
        given(transferValidator.isValid(transfer)).willReturn(true);
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(transfer.getTargetAccount()).willReturn(targetAccount);
        given(transfer.getCash()).willReturn(cashToTransfer);
        given(cashToTransfer.getValue()).willReturn(LOW_CASH_VALUE);
        given(cashService.getCurrentCash(sourceAccount)).willReturn(cashOfSourceAccount);
        given(cashOfSourceAccount.getValue()).willReturn(HIGH_CASH_VALUE);
        given(cashService.withdraw(sourceAccount, cashToTransfer)).willReturn(true);
        given(cashService.deposit(targetAccount, cashToTransfer)).willReturn(false);

        // when
        final boolean hasPerformed = testSubject.perform(transfer);

        // then
        assertThat(hasPerformed).isFalse();
    }

    @Test
    public void shouldPerformTransferIfTransferIsValidAndCashCouldBeWithdrawnAndDeposited() {
        // given
        given(transferValidator.isValid(transfer)).willReturn(true);
        given(transfer.getSourceAccount()).willReturn(sourceAccount);
        given(transfer.getTargetAccount()).willReturn(targetAccount);
        given(transfer.getCash()).willReturn(cashToTransfer);
        given(cashToTransfer.getValue()).willReturn(LOW_CASH_VALUE);
        given(cashService.getCurrentCash(sourceAccount)).willReturn(cashOfSourceAccount);
        given(cashOfSourceAccount.getValue()).willReturn(HIGH_CASH_VALUE);
        given(cashService.withdraw(sourceAccount, cashToTransfer)).willReturn(true);
        given(cashService.deposit(targetAccount, cashToTransfer)).willReturn(true);

        // when
        final boolean hasPerformed = testSubject.perform(transfer);

        // then
        assertThat(hasPerformed).isTrue();
    }

}
