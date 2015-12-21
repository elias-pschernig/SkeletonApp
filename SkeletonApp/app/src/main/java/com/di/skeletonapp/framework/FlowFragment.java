package com.di.skeletonapp.framework;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import icepick.Icepick;

/**
 * Base class to use by all the fragments in order to support automatic ice-pickling.
 */
public class FlowFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }
}
