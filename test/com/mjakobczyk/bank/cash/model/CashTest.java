package com.mjakobczyk.bank.cash.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CashTest {

    private Cash testSubject;

    @BeforeEach
    public void setUp() {
        testSubject = new Cash();
    }

    @Test
    public void shouldAddCash() {
        // given
        testSubject.setValue(1.0);
        final Cash cash = new Cash();
        cash.setValue(2.0);

        // when
        testSubject.add(cash);

        // then
        assertThat(testSubject.getValue()).isEqualTo(3.0);
    }

    @Test
    public void shouldSetDefaultInitialValueIfSubtractValueIsHigherThanCurrentValue() {
        // given
        testSubject.setValue(1.0);
        final Cash cash = new Cash();
        cash.setValue(2.0);

        // when
        testSubject.subtract(cash);

        // then
        assertThat(testSubject.getValue()).isEqualTo(0.0);
    }

    @Test
    public void shouldSubtractCash() {
        // given
        testSubject.setValue(3.0);
        final Cash cash = new Cash();
        cash.setValue(2.0);

        // when
        testSubject.subtract(cash);

        // then
        assertThat(testSubject.getValue()).isEqualTo(1.0);
    }

}
