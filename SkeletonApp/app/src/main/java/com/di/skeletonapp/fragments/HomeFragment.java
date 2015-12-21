package com.di.skeletonapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.di.skeletonapp.framework.FlowActivity;
import com.di.skeletonapp.R;
import com.di.skeletonapp.framework.FlowStack;
import com.di.skeletonapp.framework.FlowFragment;
import com.di.skeletonapp.model.Detail;
import com.di.skeletonapp.ui.HomeUi;

/**
 * The "home" fragment. This is the screen this example application starts at.
 */
public class HomeFragment extends FlowFragment {
    private HomeUi mUi;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUi = new HomeUi(view, this);
        return view;
    }

    public void onHomeButtonClicked() {
        FlowStack.setBackstackUp((FlowActivity) getActivity(), DetailFragment.newInstance(new Detail("A", "1")));
    }
}
