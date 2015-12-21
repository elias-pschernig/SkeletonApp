package com.di.skeletonapp.ui;

import android.view.View;

import com.di.skeletonapp.fragments.DetailFragment;

/**
 * Created by elias on 12/21/15.
 */
public class DetailUi {
    private final View mView;
    private final DetailFragment mFragment;

    public DetailUi(View view, DetailFragment fragment) {
        mView = view;
        mFragment = fragment;
    }
}
