package com.di.skeletonapp.ui;

import android.view.View;

import com.di.skeletonapp.fragments.TabPageFragment;

/**
 * Created by elias on 12/21/15.
 */
public class TabPageUi {
    private final View mView;
    private final TabPageFragment mFragment;

    public TabPageUi(View view, TabPageFragment fragment) {
        mView = view;
        mFragment = fragment;
    }
}
