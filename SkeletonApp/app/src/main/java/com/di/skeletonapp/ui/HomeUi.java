package com.di.skeletonapp.ui;

import android.view.View;

import com.di.skeletonapp.fragments.HomeFragment;

/**
 * Created by elias on 12/21/15.
 */
public class HomeUi {
    private final View mView;
    private final HomeFragment mFragment;

    public HomeUi(View view, HomeFragment fragment) {
        mView = view;
        mFragment = fragment;
    }
}
