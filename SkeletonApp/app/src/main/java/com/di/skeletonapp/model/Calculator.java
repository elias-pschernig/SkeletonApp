package com.di.skeletonapp.model;

import java.io.Serializable;

/**
 * Very simple example for a model.
 */

public class Calculator implements Serializable {
    double mValue;

    public Calculator(double value) {
        mValue = value;
    }

    public double getValue() {
        return mValue;
    }

    public void increment() {
        mValue += 1;
    }

    public void decrement() {
        mValue -= 1;
    }

}
