package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.R;
import com.di.skeletonapp.adapters.TabsPagerAdapter;
import com.di.skeletonapp.framework.FlowFragment;

/**
 * Example of a tabbed screen with three tabs to switch between. Each tab is a TabPageFragment.
 */
public class TabsFragment extends FlowFragment {

    private TabsPagerAdapter mAdapter;

    public TabsFragment() {
        // Required empty public constructor
    }

    public static TabsFragment newInstance(String... parameters) {
        TabsFragment fragment = new TabsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        mAdapter = new TabsPagerAdapter(getChildFragmentManager(),
                getResources().getStringArray(R.array.tabs));
        pager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(pager);
    }




}
