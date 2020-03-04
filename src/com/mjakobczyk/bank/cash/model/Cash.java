package com.mjakobczyk.bank.cash.model;

/**
 * Model representing cash.
 */
public class Cash {

    private static final double DEFAULT_INITIAL_VALUE = 0.0D;


    private double value;

    public Cash() {
        this.value = DEFAULT_INITIAL_VALUE;
    }

    public Cash(final double value) {
        this.value = value;
    }

    public Cash add(final Cash that) {
        this.value += that.getValue();
        return this;
    }

    public Cash subtract(final Cash cash) {
        if (this.getValue() < cash.getValue()) {
            this.value = DEFAULT_INITIAL_VALUE;
        } else {
            this.value -= cash.getValue();
        }
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }
}
