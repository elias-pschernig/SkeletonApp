package com.di.skeletonapp.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.di.skeletonapp.R;
import com.di.skeletonapp.fragments.DetailFragment;

public class DetailUi {
    private final View mView;
    private final DetailFragment mFragment;

    public DetailUi(View view, DetailFragment fragment) {
        mView = view;
        mFragment = fragment;

        Button button = (Button) view.findViewById(R.id.button_letter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.onLetterButton();
            }
        });

        Button button2 = (Button) view.findViewById(R.id.button_number);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.onNumberButton();
            }
        });
    }

    public void appendValue(String value) {
        TextView text = (TextView)mView.findViewById(R.id.detail_text);
        text.append(value);
    }

}
