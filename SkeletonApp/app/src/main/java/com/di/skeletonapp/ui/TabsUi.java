package com.di.skeletonapp.ui;

import android.view.View;

import com.di.skeletonapp.fragments.TabsFragment;

/**
 * Created by elias on 12/21/15.
 */
public class TabsUi {
    private final View mView;
    private final TabsFragment mFragment;

    public TabsUi(View view, TabsFragment fragment) {
        mView = view;
        mFragment = fragment;
    }
}
