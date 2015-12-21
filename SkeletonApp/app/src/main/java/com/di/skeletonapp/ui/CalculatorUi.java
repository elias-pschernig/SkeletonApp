package com.di.skeletonapp.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.di.skeletonapp.R;
import com.di.skeletonapp.fragments.CalculatorFragment;

/**
 * This is the "view" decoupled from the fragment. It has no access at all to the "model".
 * Communication is only to and from our fragment.
 */
public class CalculatorUi {

    private final View mView;
    private final CalculatorFragment mFragment;

    public CalculatorUi(View view, CalculatorFragment fragment) {
        mView = view;
        mFragment = fragment;

        Button button = (Button)mView.findViewById(R.id.button_plus);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.onPlusButtonPressed();
            }
        });

        Button button2 = (Button)mView.findViewById(R.id.button_minus);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.onMinusButtonPressed();
            }
        });
    }

    public void setValue(double value) {
        TextView text = (TextView)mView.findViewById(R.id.editText);
        text.setText(Double.toString(value));
    }
}
