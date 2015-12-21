package com.di.skeletonapp.framework;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import icepick.Icepick;

/**
 * Base class to use by all the fragments in order to support automatic ice-pickling.
 *
 * We use Icepick to restore the state when the activity gets recreated.
 */
 // TODO: Is Icepick actually necessary here? We now do re-create the UI from the model, so we are
 // TODO: not really dependant on the builtin fragment's saved instance state.
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
