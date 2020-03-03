package com.mjakobczyk.bank.account.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultAccountNumberGeneratorTest {

    private DefaultAccountNumberGenerator testSubject;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultAccountNumberGenerator();
    }

    @Test
    public void shouldGenerateRandomStringWithNotMinusSign() {
        // when
        final String result =testSubject.generate();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).doesNotContain("-");
    }

    @Test
    public void shouldGenerate32LongRandomString() {
        // when
        final String result =testSubject.generate();

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.length()).isEqualTo(32);
    }
}