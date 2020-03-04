package com.mjakobczyk.bank.cash.service.impl;

import com.mjakobczyk.bank.account.model.Account;
import com.mjakobczyk.bank.cash.model.Cash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCashRepositoryTest {

    private DefaultCashRepository testSubject;

    @Mock
    private Account account;

    @Mock
    private Cash cash;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultCashRepository();
        initMocks(this);
    }

    @Test
    public void shouldNotGetCashFromNotExistingAccount() {
        // when
        final Cash cash = testSubject.getCashFrom(null);

        // then
        assertThat(cash).isNull();
    }

    @Test
    public void shouldNotGetCashFromExistingAccountIfNoCashIsSaved() {
        // when
        final Cash cash = testSubject.getCashFrom(account);

        // then
        assertThat(cash).isNull();
    }

    @Test
    public void shouldNotSaveCashInNotExistingAccount() {
        // when
        final boolean result = testSubject.saveCashIn(null, null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotSaveNotExistingCashInExistingAccount()
    {
        // when
        final boolean result = testSubject.saveCashIn(account, null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldSaveExistingCashInExistingAccount() {
        // when
        final boolean result = testSubject.saveCashIn(account, cash);

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldGetSavedCashInAccount() {
        // given
        testSubject.saveCashIn(account, cash);

        // when
        final Cash resultCash = testSubject.getCashFrom(account);

        // then
        assertThat(resultCash).isNotNull();
        assertThat(resultCash).isEqualTo(cash);
    }

}
