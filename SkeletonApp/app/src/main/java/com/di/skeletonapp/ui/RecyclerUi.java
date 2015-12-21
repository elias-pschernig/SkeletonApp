package com.di.skeletonapp.ui;

import android.view.View;

import com.di.skeletonapp.fragments.RecyclerFragment;

/**
 * Created by elias on 12/21/15.
 */
public class RecyclerUi {
    private final View mView;
    private final RecyclerFragment mFragment;

    public RecyclerUi(View view, RecyclerFragment fragment) {
        mView = view;
        mFragment = fragment;
    }
}
