package com.di.skeletonapp.framework;

import java.io.Serializable;

/**
 * This identifies one "screen" in the application flow. For us each screen also is a fragment
 * view.
 */
public class FlowScreen implements Serializable {

    private transient FlowFragment mFragment;

    // Either null if this is a root item, or else the parent screen.
    FlowScreen parent;

    // TODO: I don't like this but the activity is needed and couldn't figure out a better
    // way to obtain a reference to it so far.
    private static transient FlowActivity mActivity;

    FlowScreen(FlowFragment fragment) {
        mFragment = fragment;
    }

    public void setParent(FlowScreen parent) {
        this.parent = parent;
    }

    public FlowScreen getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return mFragment.getClass().getSimpleName();
    }

    public static void connectActivity(FlowActivity activity) {
        mActivity = activity;
    }

    public static void disconnectActivity() {
        mActivity = null;
    }

    public static void activate() {
        if (mActivity == null)
            return;
        mActivity.showCurrentFragment();
    }

    public FlowFragment getFragment() {
        return mFragment;
    }
}
