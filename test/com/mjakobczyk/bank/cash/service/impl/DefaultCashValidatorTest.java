package com.mjakobczyk.bank.cash.service.impl;

import com.mjakobczyk.bank.cash.model.Cash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCashValidatorTest {

    private static final double CASH_VALUE_LOWER_THAN_ZERO = -1.0D;
    private static final double CASH_VALUE_ZERO = 0.0D;


    private DefaultCashValidator testSubject;

    @Mock
    private Cash cash;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultCashValidator();
        initMocks(this);
    }

    @Test
    public void shouldNotBeValidIfCashIsNull() {
        // when
        final boolean result = testSubject.isValid(null);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldNotBeValidIfCashHasValueLowerThanZero() {
        // given
        given(cash.getValue()).willReturn(CASH_VALUE_LOWER_THAN_ZERO);

        // when
        final boolean result = testSubject.isValid(cash);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldBeValidIfCashHasAtLeastZeroValue() {
        // given
        given(cash.getValue()).willReturn(CASH_VALUE_ZERO);

        // when
        final boolean result = testSubject.isValid(cash);

        // then
        assertThat(result).isTrue();
    }

}
