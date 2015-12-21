package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.framework.FlowFragment;
import com.di.skeletonapp.ui.TabsUi;

/**
 * Example of a tabbed screen with three tabs to switch between. Each tab is a TabPageFragment.
 */
public class TabsFragment extends FlowFragment {

    private TabsUi mUi;

    public static TabsFragment newInstance() {
        return new TabsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        mUi = new TabsUi(view, this);
        return view;
    }
}
