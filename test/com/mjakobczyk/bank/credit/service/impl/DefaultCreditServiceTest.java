package com.mjakobczyk.bank.credit.service.impl;

import com.mjakobczyk.bank.credit.model.Credit;
import com.mjakobczyk.bank.credit.service.CreditValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultCreditServiceTest {

    @InjectMocks
    private DefaultCreditService testSubject;

    @Mock
    private CreditValidator creditValidator;

    @Mock
    private Credit credit;

    @BeforeEach
    public void setUp() {
        testSubject = new DefaultCreditService();
        initMocks(this);
    }

    @Test
    public void shouldNotTakeCreditForInvalidCreditData() {
        //given
        given(creditValidator.isValid(credit)).willReturn(false);

        // when
        final boolean result = testSubject.take(credit);

        // then
        assertThat(result).isFalse();
    }

}
