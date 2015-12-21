package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.ui.TabPageUi;
import com.digintent.framework.FlowFragment;

/**
 * A simple screen showing on one of the tabs of the tabs screen.
 */
public class TabPageFragment extends FlowFragment {

    private TabPageUi mUi;
    private String mTitle;

    public static TabPageFragment newInstance(String title) {
        TabPageFragment fragment = new TabPageFragment();
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_page, container, false);
        mUi = new TabPageUi(view, this);
        mUi.appendToTitle(mTitle);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}
