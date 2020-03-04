package com.mjakobczyk.bank.cash.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.account.service.AccountValidator;
import com.mjakobczyk.bank.cash.model.Cash;
import com.mjakobczyk.bank.cash.service.CashRepository;
import com.mjakobczyk.bank.cash.service.CashValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCashServiceTest {

    private static final double LOW_CASH_VALUE = 1.0D;
    private static final double HIGH_CASH_VALUE = 3.0D;


    @InjectMocks
    private DefaultCashService testSubject;

    @Mock
    private AccountValidator accountValidator;

    @Mock
    private CashValidator cashValidator;

    @Mock
    private CashRepository cashRepository;

    @Mock
    private Account account;

    @Mock
    private Cash accountCash;

    @Mock
    private Cash additionalCash;

    @Mock
    private Cash withdrawalCash;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultCashService();
        initMocks(this);
    }

    @Test
    public void shouldNotDepositCashInAccountIfAccountAndCashAreInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(false);
        given(cashValidator.isValid(accountCash)).willReturn(false);

        // when
        final boolean result = testSubject.deposit(null, null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotDepositCashInAccountIfOnlyAccountIsInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(false);
        given(cashValidator.isValid(accountCash)).willReturn(true);

        // when
        final boolean result = testSubject.deposit(null, accountCash);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotDepositCashInAccountIfOnlyCashIsInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(cashValidator.isValid(accountCash)).willReturn(false);

        // when
        final boolean result = testSubject.deposit(account, null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldSaveNewCashIfCurrentCashDoesNotExist() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(cashValidator.isValid(additionalCash)).willReturn(true);
        given(cashRepository.getCashFrom(account)).willReturn(null);
        given(cashRepository.saveCashIn(account, additionalCash)).willReturn(true);

        // when
        final boolean result = testSubject.deposit(account, additionalCash);

        // then
        assertThat(result).isTrue();
        then(cashRepository).should().saveCashIn(account, additionalCash);
    }

    @Test
    public void shouldAddNewCashToTheCurrentCashIfCurrentCashExists() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(cashValidator.isValid(additionalCash)).willReturn(true);
        given(cashRepository.getCashFrom(account)).willReturn(accountCash);
        given(cashRepository.saveCashIn(account, accountCash)).willReturn(true);

        // when
        final boolean result = testSubject.deposit(account, additionalCash);

        // then
        assertThat(result).isTrue();
        then(cashRepository).should().saveCashIn(account, accountCash);
    }

    @Test
    public void shouldNotWithdrawCashInAccountIfAccountAndCashAreInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(false);
        given(cashValidator.isValid(accountCash)).willReturn(false);

        // when
        final boolean result = testSubject.withdraw(null, null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotWithdrawCashInAccountIfOnlyAccountIsInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(false);
        given(cashValidator.isValid(accountCash)).willReturn(true);

        // when
        final boolean result = testSubject.withdraw(null, accountCash);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotWithdrawCashInAccountIfOnlyCashIsInvalid() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(cashValidator.isValid(accountCash)).willReturn(false);

        // when
        final boolean result = testSubject.withdraw(account, null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotWithdrawCashIfCurrentCashDoesNotExist() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(cashValidator.isValid(additionalCash)).willReturn(true);
        given(cashRepository.getCashFrom(account)).willReturn(null);

        // when
        final boolean result = testSubject.withdraw(account, additionalCash);

        // then
        assertThat(result).isFalse();
        then(cashRepository).should().getCashFrom(account);
    }

    @Test
    public void shouldNotWithdrawCashIfCurrentCashIsLowerThanWithdrawalValue() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(cashValidator.isValid(withdrawalCash)).willReturn(true);
        given(cashRepository.getCashFrom(account)).willReturn(accountCash);
        given(accountCash.getValue()).willReturn(LOW_CASH_VALUE);
        given(withdrawalCash.getValue()).willReturn(HIGH_CASH_VALUE);

        // when
        final boolean result = testSubject.withdraw(account, withdrawalCash);

        // then
        assertThat(result).isFalse();
        then(cashRepository).should().getCashFrom(account);
    }

    @Test
    public void shouldWithdrawCashIfCurrentCashIsEqualTotWithdrawalValue() {
        // given
        given(accountValidator.isValid(account)).willReturn(true);
        given(cashValidator.isValid(withdrawalCash)).willReturn(true);
        given(cashRepository.getCashFrom(account)).willReturn(accountCash);
        given(accountCash.getValue()).willReturn(LOW_CASH_VALUE);
        given(withdrawalCash.getValue()).willReturn(LOW_CASH_VALUE);
        given(cashRepository.saveCashIn(account, accountCash)).willReturn(true);

        // when
        final boolean result = testSubject.withdraw(account, withdrawalCash);

        // then
        assertThat(result).isTrue();
        then(accountCash).should().subtract(withdrawalCash);
        then(cashRepository).should().getCashFrom(account);
    }

}
