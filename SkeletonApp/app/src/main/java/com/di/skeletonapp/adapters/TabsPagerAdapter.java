package com.di.skeletonapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.di.skeletonapp.fragments.TabPageFragment;

import java.util.ArrayList;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private String titles[];
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public static TabsPagerAdapter newInstance(FragmentManager fragmentManager,
                                               String[] titles) {

        return new TabsPagerAdapter(fragmentManager, titles);
    }

    public TabsPagerAdapter(FragmentManager fragmentManager, String[] titles) {
        super(fragmentManager);
        this.titles = titles;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        FragmentManager manager = ((Fragment) object).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) object);
        trans.commit();
        super.destroyItem(container, position, object);
    }

    public Fragment getStoredItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return TabPageFragment.newInstance("tab number " + position, "test");
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

}