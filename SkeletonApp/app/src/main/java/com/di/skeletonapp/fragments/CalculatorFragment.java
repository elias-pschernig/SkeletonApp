package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.framework.FlowFragment;
import com.di.skeletonapp.model.Calculator;
import com.di.skeletonapp.ui.CalculatorUi;

import icepick.State;

public class CalculatorFragment extends FlowFragment {
    /* This is the "model". */
    @State Calculator mCalculator;

    /* This is the "view". */
    private CalculatorUi mUi;

    public static CalculatorFragment newInstance(Calculator model) {
        CalculatorFragment fragment = new CalculatorFragment();
        fragment.mCalculator = model;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        mUi = new CalculatorUi(view, this);
        return view;
    }

    public void onPlusButtonPressed() {
        mCalculator.increment();
        mUi.setValue(mCalculator.getValue());
    }

    public void onMinusButtonPressed() {
        mCalculator.decrement();
        mUi.setValue(mCalculator.getValue());
    }
}
