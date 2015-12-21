package com.di.skeletonapp.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.di.skeletonapp.R;
import com.di.skeletonapp.adapters.TabsPagerAdapter;
import com.di.skeletonapp.fragments.TabsFragment;

public class TabsUi {
    private final View mView;
    private final TabsFragment mFragment;
    private TabsPagerAdapter mAdapter;

    public TabsUi(View view, TabsFragment fragment) {
        mView = view;
        mFragment = fragment;

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        mAdapter = new TabsPagerAdapter(mFragment.getChildFragmentManager(),
                mFragment.getResources().getStringArray(R.array.tabs));
        pager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(pager);
    }
}
