package com.di.skeletonapp.ui;

import android.view.View;
import android.widget.Button;

import com.di.skeletonapp.R;
import com.di.skeletonapp.fragments.HomeFragment;

public class HomeUi {
    private final View mView;
    private final HomeFragment mFragment;

    public HomeUi(View view, HomeFragment fragment) {
        mView = view;
        mFragment = fragment;

        Button button = (Button)view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.onHomeButtonClicked();
            }
        });
    }
}
